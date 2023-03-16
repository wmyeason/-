package com.ashuo.scms.service.impl;

import com.ashuo.scms.entity.World;
import com.ashuo.scms.mapper.WorldMapper;
import com.ashuo.scms.service.WorldService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Objects;

/**
 * (World)表服务实现类
 *
 * @author makejava
 * @since 2023-02-20 19:39:56
 */
@Service
@Slf4j
public class WorldServiceImpl extends ServiceImpl<WorldMapper, World> implements WorldService {

    @Autowired
    private WorldMapper worldMapper;

    @Override
    public int countResTeam() {
        LambdaQueryWrapper<World> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(World::getStatus,1);
        return this.count(wrapper);
    }

    @Override
    public World toGetOneAndId(int i) {
        return worldMapper.queryOneId(i);
    }

    @Override
    public boolean toRegister(World world) {
        return worldMapper.toRegister(world);
    }

    @Override
    public boolean isRegister(Integer id) {
        World register = worldMapper.isRegister(id);
        if(Objects.isNull(register)){
            //如果是空  就可以报名
            return  false;
        }
        return register.getStatus()==1 ;
    }

    @Override
    public void toCancel(Integer ids) {
        LambdaQueryWrapper<World> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(World::getTId,ids);
        World wr=new World();
        wr.setStatus(0);
        this.update(wr,wrapper);
    }

    @Override
    public boolean clearWorld() {
        LambdaUpdateWrapper<World> wrapper=new LambdaUpdateWrapper<>();
        wrapper.set(World::getStatus,0)
                .set(World::getTId,null);
        boolean update = this.update(wrapper);
        return update;
    }
}
