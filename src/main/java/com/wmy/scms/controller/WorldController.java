package com.wmy.scms.controller;

import com.wmy.scms.common.lang.ServerResponse;
import com.wmy.scms.entity.World;
import com.wmy.scms.service.RaceService;
import com.wmy.scms.service.WorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * (World)表控制层
 *
 * @author makejava
 * @since 2023-02-20 19:39:57
 */
@Api(tags = "世界杯项目接口")
@Slf4j
@RestController
@RequestMapping("world")
public class WorldController {
 
    @Autowired
    private WorldService worldService;

    @Autowired
    private RaceService raceService;

    @ApiOperation("查询报名了的队伍数")
    @GetMapping("/getTeams")
    public ServerResponse getTeams(){
        //查询有多少报名了的队伍
        int i = worldService.countResTeam();
        return ServerResponse.createBySuccess(i);
    }

    @ApiOperation("报名世界杯")
    @PutMapping("/registerWorld/{id}")
    public ServerResponse registerWorld(@PathVariable("id") Integer id){
        //先判断是否已经报过名了
        boolean b = worldService.isRegister(id);
        if(b){//说明已经报名过了 返回无法报名
            return  ServerResponse.createByErrorMessage("已经报名过了!");
        }
        //给当前用户的队伍 报名 参加
        World world= worldService.toGetOneAndId(id);
        //得到了当前报名的  队伍 ID  |第几支队伍
        world.setTId(id);
        //将这支队伍 报名   |  status 改为1
        boolean flag = worldService.toRegister(world);
        return ServerResponse.createBySuccess(flag);
    }

    @ApiOperation("取消报名")
    @GetMapping("/cancelRegister/{id}")
    public ServerResponse cancelRegister(@PathVariable("id") Integer id){
        //先判断是否已经报过名了
        boolean b = worldService.isRegister(id);
        if(!b){
            return  ServerResponse.createByErrorMessage("您的队伍还没有报名!");
        }else{
            worldService.toCancel(id);
            return  ServerResponse.createBySuccess();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("重置比赛")
    @GetMapping("/toReset")
    public ServerResponse toReset(){
        //重置比赛不是删除所有记录   因为记录只能有16支队  15条比赛信息
        //所以要做的是将这些信息的状态都设置为对应的  0  或者  -1

         boolean b= worldService.clearWorld();
         if(!b){
             return ServerResponse.createByErrorMessage("重置报名队伍失败");
         }
         b= raceService.clearRace();
        if(!b){
            return ServerResponse.createByErrorMessage("重置赛程失败");
        }

        return ServerResponse.createBySuccess();
    }

    @ApiOperation("获取报名了的队伍信息")
    @GetMapping("/getRegister")
    public ServerResponse getRegister(){

        return ServerResponse.createBySuccess();
    }
}

