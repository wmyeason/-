package com.wmy.scms.service;

import com.wmy.scms.entity.Race;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Race)表服务接口
 *
 * @author makejava
 * @since 2023-02-21 12:42:31
 */
public interface RaceService extends IService<Race>{

    void startRace(List<Race> raceList);

    IPage<Race> getRaceByIPage(Page<Race> racePage, Race race);

    boolean updateRace(Race race);

    Integer getStartRace(int i);

    List<Race> getSixteen(int i);

    boolean clearRace();
}
