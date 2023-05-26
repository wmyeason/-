package com.wmy.scms.controller;

import com.wmy.scms.common.lang.ServerResponse;
import com.wmy.scms.dto.RaceDto;
import com.wmy.scms.entity.*;
import com.wmy.scms.service.*;
import com.wmy.scms.util.RewardPic;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Race)表控制层
 *
 * @author makejava
 * @since 2023-02-21 12:42:31
 */
@Api("世界杯比赛赛程")
@Slf4j
@RestController
@RequestMapping("race")
public class RaceController {

    @Autowired
    private RaceService raceService;

    @Autowired
    private WorldService worldService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CupService cupService;

    @Autowired
    private RewardService rewardService;

    @ApiOperation("设置对战情况")
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/startRace")
    public Serializable startRace() {
        //获取16支报名了的队伍
        List<World> worldList = worldService.list();
        List<Race> raceList = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < worldList.size(); i += 2) {
            Race race = new Race();
            race.setId(index++);
            race.setStatus(1);//表示在16强阶段  如果裁判设置赢了就将胜利的人设置2  表示2阶段4强
            race.setAId(worldList.get(i).getTId());
            race.setAName(teamService.getNameById(worldList.get(i).getTId()));
            race.setBId(worldList.get(i + 1).getTId());
            race.setBName(teamService.getNameById(worldList.get(i + 1).getTId()));
            raceList.add(race);
        }
        //对 比赛赛程表进行修改操作
        raceService.startRace(raceList);

        RaceDto raceDto = new RaceDto();
        //查询现有的16强赛事
        List<Race> six = raceService.getSixteen(1);
        raceDto.setSixteen(six);
        List<Race> quarter = raceService.getSixteen(2);
        raceDto.setQuarter(quarter);
        List<Race> semi = raceService.getSixteen(3);
        raceDto.setSemi(semi);
        List<Race> Final = raceService.getSixteen(4);
        raceDto.setFinal(Final);
        return ServerResponse.createBySuccess(raceDto);
    }

    @ApiOperation("获取所有比赛信息")
    @GetMapping("/queryRace")
    public ServerResponse queryRace(QueryInfo queryInfo, Race race) {
        //IPage 分页查询
        Page<Race> racePage = new Page<>(queryInfo.getCurrentPage(), queryInfo.getPageSize());
        IPage<Race> List = raceService.getRaceByIPage(racePage, race);
        return ServerResponse.createBySuccess(List);
    }

    @ApiOperation("设置比赛结果")
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/updateRace")
    public ServerResponse updateRace(@RequestBody Race race) {
        //设置这场比赛的比分
        boolean b = raceService.updateRace(race);
        if (!b) {
            return ServerResponse.createByErrorCodeMessage(400, "失败！");
        }
        //通过赢家设置4强的比分
        //先设置是下 一场的A队还是B队  可以根据ID是否是偶数判断是否是主客队
        Race win = new Race();
        if (race.getId() % 2 == 1) {
            //是偶数就是下一场的A队   奇数就是下一场的B队
            if (race.getResult().equals(race.getAId())) {
                //如果胜利者是A队  赢家就设置A的ID和name
                win.setAId(race.getAId());
                win.setAName(race.getAName());
            } else {
                win.setAId(race.getBId());
                win.setAName(race.getBName());
            }
        } else {
            if (race.getResult().equals(race.getAId())) {
                //如果胜利者是A队  赢家就设置A的ID和name
                win.setBId(race.getAId());
                win.setBName(race.getAName());
            } else {
                win.setBId(race.getBId());
                win.setBName(race.getBName());
            }
        }
        //查找下一场比赛的ID 然后注入数据进去
        Integer r = raceService.getStartRace(race.getStatus() + 1);
        win.setId(r);
        boolean b1 = raceService.updateRace(win);


        if (race.getId() == 15) {
            //先给每一届的比赛保存成绩
            Integer winId = race.getAId().equals(race.getResult()) ? race.getAId() : race.getBId();
            Integer loseId = race.getAId().equals(winId) ? race.getBId() : race.getAId();
            String winName = race.getAId().equals(race.getResult()) ? race.getAName() : race.getAName();
            String loseName = race.getAId().equals(race.getResult()) ? race.getBName() : race.getAName();
            Date now = new Date();

            Cup winner = new Cup();
            winner.setTeamId(winId);
            winner.setTeamName(winName);
            winner.setType(1);
            boolean b2 = cupService.saveTeam(winner);

            Cup loser = new Cup();
            loser.setTeamId(loseId);
            loser.setTeamName(loseName);
            loser.setType(2);
            boolean b3 = cupService.saveTeam(loser);

            if(!b2&&!b3)return ServerResponse.createByError();

            String seasonName = "";
            String raceName = "足球比赛";
            String win_url = RewardPic.DrawPic(1, winName, seasonName, raceName);
            String lose_url = RewardPic.DrawPic(2, loseName, seasonName, raceName);

            Reward wins = new Reward();
            wins.setCreateTime(now);
            wins.setTeamId(winId);
            wins.setImgUrl(win_url);
            boolean save = rewardService.save(wins);


            Reward loses = new Reward();
            loses.setCreateTime(now);
            loses.setTeamId(loseId);
            loses.setImgUrl(lose_url);
            boolean save1 = rewardService.save(loses);
            if (!save1&&!save) ServerResponse.createByError();
        }
        if (!b1&&!b) ServerResponse.createByError();
        return ServerResponse.createBySuccess();
    }


}

