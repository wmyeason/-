package com.wmy.scms.service;

import com.wmy.scms.dto.AthleteScoreDto;
import com.wmy.scms.entity.Score;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-04-05
 */
public interface ScoreService {

    IPage<Score> getScoreByScoreCondition(Page<Score> page, Score score);


    IPage<AthleteScoreDto> getAthleteScoreDto(Page<AthleteScoreDto> page, Score score);


    List<Score> getScoreByItemIdLimit(int itemId, String condition);

    Score getOneScoreByScoreId(int scoreId);

    int addScore(Score score);

    int modifyScore(Score score);


}
