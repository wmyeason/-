package com.ashuo.scms.mapper;

import com.ashuo.scms.entity.Reward;
import com.ashuo.scms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * (Reward)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-20 20:38:19
 */

public interface RewardMapper extends BaseMapper<Reward>{


    IPage<Reward> getRewardByIds(Page<Reward> rewardPage, @Param("user")User user);
}

