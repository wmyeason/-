package com.wmy.scms.service;

import com.wmy.scms.entity.Season;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-04-07
 */
public interface SeasonService extends IService<Season> {

    IPage<Season> getSeasonByCondition(Page<Season> page, Season season);

    Season getSeasonById(Season season);

    int addSeason(Season season);

    int removeSeason(int seasonId);

    int modifySeason(Season season);
}
