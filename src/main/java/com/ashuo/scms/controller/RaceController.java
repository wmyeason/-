package com.ashuo.scms.controller;

import com.ashuo.scms.common.lang.ServerResponse;
import com.ashuo.scms.dto.RaceDto;
import com.ashuo.scms.entity.QueryInfo;
import com.ashuo.scms.entity.Race;
import com.ashuo.scms.entity.World;
import com.ashuo.scms.service.RaceService;
import com.ashuo.scms.service.TeamService;
import com.ashuo.scms.service.WorldService;
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

    @ApiOperation("设置对战情况")
    @GetMapping("/startRace")
    public Serializable startRace(){
        //获取16支报名了的队伍
        List<World> worldList = worldService.list();
        List<Race> raceList=new ArrayList<>();
        int index=1;
        for (int i = 0; i <worldList.size() ; i+=2) {
            Race race=new Race();
            race.setId(index++);
            race.setStatus(1);//表示在16强阶段  如果裁判设置赢了就将胜利的人设置2  表示2阶段4强
            race.setAId(worldList.get(i).getTId());
            race.setAName(teamService.getNameById(worldList.get(i).getTId()));
            race.setBId(worldList.get(i+1).getTId());
            race.setBName(teamService.getNameById(worldList.get(i+1).getTId()));
            raceList.add(race);
        }
        //对 比赛赛程表进行修改操作
        raceService.startRace(raceList);

        RaceDto raceDto=new RaceDto();
        //查询现有的16强赛事
        List<Race> six= raceService.getSixteen(1);
        raceDto.setSixteen(six);
        List<Race> quarter= raceService.getSixteen(2);
        raceDto.setQuarter(quarter);
        List<Race> semi= raceService.getSixteen(3);
        raceDto.setSemi(semi);
        List<Race> Final= raceService.getSixteen(4);
        raceDto.setFinal(Final);
        return ServerResponse.createBySuccess(raceDto);
    }

    @ApiOperation("获取所有比赛信息")
    @GetMapping("/queryRace")
    public ServerResponse queryRace(QueryInfo queryInfo,Race race){
        //IPage 分页查询
        Page<Race> racePage=new Page<>(queryInfo.getCurrentPage(),queryInfo.getPageSize());
        IPage<Race> List=raceService.getRaceByIPage(racePage,race);
        return ServerResponse.createBySuccess(List);
    }

    @ApiOperation("设置比赛结果")
    @Transactional
    @PutMapping("/updateRace")
    public ServerResponse updateRace(@RequestBody Race race){
        //设置这场比赛的比分
        boolean b = raceService.updateRace(race);
        //通过赢家设置4强的比分
        //先设置是下一场的A队还是B队  可以根据ID是否是偶数判断是否是主客队
        Race win=new Race();
        if(race.getId()%2==1){
            //是偶数就是下一场的A队   奇数就是下一场的B队
            if(race.getResult().equals(race.getAId())){
                //如果胜利者是A队  赢家就设置A的ID和name
                win.setAId(race.getAId());
                win.setAName(race.getAName());
            }else{
                win.setAId(race.getBId());
                win.setAName(race.getBName());
            }
        }else{
            if(race.getResult().equals(race.getAId())){
                //如果胜利者是A队  赢家就设置A的ID和name
                win.setBId(race.getAId());
                win.setBName(race.getAName());
            }else{
                win.setBId(race.getBId());
                win.setBName(race.getBName());
            }
        }
        //查找下一场比赛的ID 然后注入数据进去
        Integer r= raceService.getStartRace(race.getStatus()+1);
        win.setId(r);
        boolean b1 = raceService.updateRace(win);
        return  ServerResponse.createBySuccess();
    }


}

