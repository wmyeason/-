package com.ashuo.scms.service.impl;


import com.ashuo.scms.entity.QueryInfo;
import com.ashuo.scms.entity.Race;
import com.ashuo.scms.mapper.RaceMapper;
import com.ashuo.scms.service.RaceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * (Race)表服务实现类
 *
 * @author makejava
 * @since 2023-02-21 12:42:31
 */
@Service
@Slf4j
public class RaceServiceImpl extends ServiceImpl<RaceMapper, Race> implements RaceService {

    @Autowired
    private RaceMapper raceMapper;

    @Override
    public void startRace(List<Race> raceList) {
        raceMapper.updateList(raceList);
    }

    @Override
    public IPage<Race> getRaceByIPage(Page<Race> racePage, Race race) {
        return raceMapper.getRaceByIPages(racePage,race);
    }

    @Override
    public boolean updateRace(Race race) {
        return raceMapper.updateRace(race);
    }

    @Override
    public Integer getStartRace(int i) {
        return raceMapper.getStartRace(i);
    }

    @Override
    public List<Race> getSixteen(int i) {
        LambdaQueryWrapper<Race> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Race::getStatus,i);
        List<Race> list = this.list(wrapper);
        return list;
    }
}
