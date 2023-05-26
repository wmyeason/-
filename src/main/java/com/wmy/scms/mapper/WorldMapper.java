package com.wmy.scms.mapper;

import com.wmy.scms.entity.World;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * (World)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-20 19:39:57
 */

public interface WorldMapper extends BaseMapper<World>{

    World queryOneId(Integer id);

    boolean toRegister(World world);

    World isRegister(Integer id);
}

