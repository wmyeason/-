package com.wmy.scms.service;

import com.wmy.scms.entity.Athlete;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-04-05
 */
public interface AthleteService {

    IPage<Athlete> getAthleteByCondition(Page<Athlete> page, Athlete athlete);

    int addAthlete(Athlete athlete);

    int modifyAthlete(Athlete athlete);

    int removeAthlete(int athleteId);


    boolean passCheck(Integer id, Integer status);

    Integer isGetReward(Integer itemId);
}