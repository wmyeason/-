package com.wmy.scms.mapper;

import com.wmy.scms.entity.Season;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 *
 * @since 2023-04-07
 */
@Mapper
public interface SeasonMapper extends BaseMapper<Season> {
    IPage<Season> querySeasonBySeasonCondition(Page<Season> page, @Param("season") Season season);

    Season querySeasonById(Season season);

    int insertSeason(Season season);

    int deleteSeason(int seasonId);

    int updateSeason(@Param("season") Season season);
}
