package com.wmy.scms.service;

import com.wmy.scms.entity.Reward;
import com.wmy.scms.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (Reward)表服务接口
 *
 * @author makejava
 * @since 2023-03-20 20:38:19
 */
public interface RewardService extends IService<Reward>{

    IPage<Reward> getRewardById(User user, Page<Reward> rewardPage);
}
