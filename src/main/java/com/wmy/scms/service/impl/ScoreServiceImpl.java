package com.wmy.scms.service.impl;

import com.wmy.scms.dto.AthleteScoreDto;
import com.wmy.scms.entity.Score;
import com.wmy.scms.mapper.ScoreMapper;
import com.wmy.scms.service.ScoreService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 *
 * @since 2023-04-05
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public IPage<Score> getScoreByScoreCondition(Page<Score> page, Score score) {
        if (score == null) {
            return null;
        }
        IPage<Score> scoreList = scoreMapper.queryScoreByScoreCondition(page, score);
        return scoreList;
    }


    @Override
    public IPage<AthleteScoreDto> getAthleteScoreDto(Page<AthleteScoreDto> page, Score score) {
        IPage<AthleteScoreDto> scoreDtoList = scoreMapper.queryAthleteScoreDto(page, score);
        return scoreDtoList;
    }


    @Override
    public List<Score> getScoreByItemIdLimit(int itemId, String condition) {
        if (itemId == 0) {
            return null;
        }
        List<Score> scoreList = scoreMapper.queryScoreByItemIdLimit(itemId, condition);
        return scoreList;
    }

    @Override
    public Score getOneScoreByScoreId(int scoreId) {
        if (scoreId == 0) {
            return null;
        }
        Score score = scoreMapper.queryOneScoreByScoreId(scoreId);
        return score;
    }


    @Override
    public int addScore(Score score) {
        if (score == null) {
            return 0;
        } else {
            int effNum = scoreMapper.insertScore(score);
            if (effNum != 1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }

    @Override
    public int modifyScore(Score score) {
        if (score == null) {
            return 0;
        } else {
            int effNum = scoreMapper.updateScore(score);
            if (effNum != 1) {
                return 0;
            } else {
                return effNum;
            }
        }
    }


}
