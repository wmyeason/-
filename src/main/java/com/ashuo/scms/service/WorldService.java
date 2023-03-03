package com.ashuo.scms.service;

import com.ashuo.scms.entity.World;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (World)表服务接口
 *
 * @author makejava
 * @since 2023-02-20 19:39:57
 */
public interface WorldService extends IService<World>{

    int countResTeam();

    World toGetOneAndId(int i);

    boolean toRegister(World world);

    boolean isRegister(Integer id);

    void toCancel(Integer ids);
}
