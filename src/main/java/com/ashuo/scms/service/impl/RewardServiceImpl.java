package com.ashuo.scms.service.impl;

import com.ashuo.scms.entity.Reward;
import com.ashuo.scms.entity.User;
import com.ashuo.scms.mapper.RewardMapper;
import com.ashuo.scms.service.RewardService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (Reward)表服务实现类
 *
 * @author makejava
 * @since 2023-03-20 20:38:19
 */
@Service
@Slf4j
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements RewardService {
    @Autowired
    private RewardMapper rewardMapper;

    @Override
    public IPage<Reward> getRewardById(User user, Page<Reward> rewardPage) {
        return rewardMapper.getRewardByIds(rewardPage,user);
    }
}
