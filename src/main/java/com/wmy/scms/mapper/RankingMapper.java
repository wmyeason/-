package com.wmy.scms.mapper;

import com.wmy.scms.entity.Ranking;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 *
 * @since 2023-04-05
 */
@Mapper
public interface RankingMapper {
    //增加单个Ranking
    int insertRanking(List<Ranking> list);

    int deleteRankingByItemId(@Param("itemId") Integer itemId);

    //返回团体排名和总积分
    IPage<Ranking> queryTeamTotalRanking(Page<Ranking> page, @Param("ranking") Ranking ranking);

    //返回个人排名和总积分
    IPage<Ranking> queryUserTotalRanking(Page<Ranking> page, @Param("ranking") Ranking ranking);

    int deleteRankingByAId(int athleteId);
}
