package com.wmy.scms.mapper;

import com.wmy.scms.dto.AthleteScoreDto;
import com.wmy.scms.entity.Score;
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
public interface ScoreMapper {
    //增加单个Score
    int insertScore(Score score);

    //修改Score
    int updateScore(Score score);

    //按条件查询Score
    IPage<Score> queryScoreByScoreCondition(Page<Score> page, @Param("score") Score score);

    //查询分数排名并限制数量
    List<Score> queryScoreByItemIdLimit(@Param("itemId") Integer itemId, @Param("condition") String condition);

    //获取单个Score
    Score queryOneScoreByScoreId(@Param("scoreId") Integer scoreId);

    //查询返回Dto对象
    IPage<AthleteScoreDto> queryAthleteScoreDto(Page<AthleteScoreDto> page, @Param("score") Score score);

    int deleteScoreByAId(int athleteId);
}
