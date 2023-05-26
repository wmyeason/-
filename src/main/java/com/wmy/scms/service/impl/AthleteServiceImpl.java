package com.wmy.scms.service.impl;

import com.wmy.scms.common.consant.Consant;
import com.wmy.scms.entity.Athlete;
import com.wmy.scms.entity.Item;
import com.wmy.scms.entity.Season;
import com.wmy.scms.mapper.*;
import com.wmy.scms.service.AthleteService;
import com.wmy.scms.service.SeasonService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 *
 * @since 2023-04-05
 */
@Service
public class AthleteServiceImpl implements AthleteService {

    @Autowired
    AthleteMapper athleteMapper;

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    RankingMapper rankingMapper;

    @Autowired
    SeasonService seasonService;

    @Autowired
    RecordMapper recordMapper;

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public IPage<Athlete> getAthleteByCondition(Page<Athlete> page, Athlete athleteCondition) {
        if (athleteCondition == null) {
            return null;
        }
        IPage<Athlete> athleteList = athleteMapper.queryAthleteByAthleteCondition(page, athleteCondition);
        return athleteList;
    }


    @Override
    public int addAthlete(Athlete athlete) {
        if (athlete == null) {
            return 0;
        } else {
            int effNum = athleteMapper.insertAthlete(athlete);
            //将对应项目报名数量增加一
            Item item = itemMapper.queryOneItemByItemCondition(athlete.getItem());
            item.setAthleteAmount(item.getAthleteAmount() + 1);
            int effNum2 = itemMapper.updateItem(item);
            //将本届运动会的报名人数增加一
            Season season = seasonService.getSeasonById(item.getSeason());
            season.setSeasonAthleteAmount(season.getSeasonAthleteAmount() + 1);
            int effNum3 = seasonService.modifySeason(season);
            if (effNum != 1 || effNum2 != 1 || effNum3 != 1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }

    @Override
    public int modifyAthlete(Athlete athlete) {
        if (athlete == null) {
            return 0;
        } else {
            int effNum = athleteMapper.updateAthlete(athlete);
            if (effNum != 1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removeAthlete(int athleteId) {
        if (athleteId == 0) {
            return 0;
        } else {
            Athlete athlete = new Athlete();
            athlete.setAthleteId(athleteId);
            IPage<Athlete> athleteList = athleteMapper.queryAthleteByAthleteCondition(new Page<>(Consant.MINCURRENTPAGE, Consant.MINPAGESIZE), athlete);
            athlete = athleteList.getRecords().get(0);
            Item temp = (athlete.getItem());
            Item item = itemMapper.queryOneItemByItemCondition(temp);
            //将对应项目报名数量减一
            item.setAthleteAmount(item.getAthleteAmount() - 1);
            int effNum = itemMapper.updateItem(item);
            int effNum1 = recordMapper.deleteRecoerdByAId(athleteId);
            int effNum4 = rankingMapper.deleteRankingByAId(athleteId);
            int effNum5 = scoreMapper.deleteScoreByAId(athleteId);
            int effNum2 = athleteMapper.deleteAthlete(athleteId);
            //将本届运动会的报名人数减一
            Season season = seasonService.getSeasonById(item.getSeason());
            season.setSeasonAthleteAmount(season.getSeasonAthleteAmount() - 1);
            int effNum3 = seasonService.modifySeason(season);
            if (effNum < 1 && effNum2 <1 && effNum3 < 1 &&effNum1<1&&effNum4<1&&effNum5<1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }

    @Override
    public boolean passCheck(Integer id, Integer status) {
        Athlete athlete=new Athlete();
        athlete.setAthleteId(id);
        athlete.setCheckStatus(status);
        int i = athleteMapper.updateAthlete(athlete);
        return i==1;
    }

    @Override
    public Integer isGetReward(Integer itemId) {
        return athleteMapper.countPeos(itemId);
    }
}
