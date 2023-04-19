package com.ashuo.scms.controller;


import com.ashuo.scms.common.lang.ServerResponse;
import com.ashuo.scms.dto.TeamGetDto;
import com.ashuo.scms.dto.UserAndTeamDto;
import com.ashuo.scms.entity.QueryInfo;
import com.ashuo.scms.entity.Team;
import com.ashuo.scms.entity.User;
import com.ashuo.scms.service.RaceService;
import com.ashuo.scms.service.TeamService;
import com.ashuo.scms.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AShuo
 * @since 2021-04-05
 */

@Api(tags = "团队接口")
@RestController
@Slf4j
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @ApiOperation("查询团体")
    @GetMapping("/queryTeam")
    @RequiresAuthentication
    public ServerResponse queryTeam(QueryInfo queryInfo, Team team) {
        team.setTeamName(queryInfo.getQuery());
        Page<Team> page = new Page<>(queryInfo.getCurrentPage(), queryInfo.getPageSize());
        IPage<Team> teamList = teamService.getAllTeam(page, team);
        return ServerResponse.createBySuccess(teamList);
    }


    @ApiOperation("添加团体")
    @PostMapping("/addTeam")
    @RequiresRoles(value = {"1"})
    public ServerResponse addTeam(@RequestBody Team team) {

        if (team == null || team.getTeamName() == null) {
            return ServerResponse.createByErrorCodeMessage(400, "添加失败，团体信息为空");
        }

        if (teamService.getTeamByCondition(team) != null) {
            return ServerResponse.createByErrorCodeMessage(400, "添加失败，团体名称已存在");
        }
        //设置创建时间
        team.setCreateTime(LocalDateTime.now());
        team.setEditTime(LocalDateTime.now());
        int effNum = 0;
        try {
            effNum = teamService.addTeam(team);
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(400, "添加失败");
        }
        if (effNum == 0) {
            return ServerResponse.createByErrorCodeMessage(400, "添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @ApiOperation("删除团体")
    @DeleteMapping("/deleteTeam")
    @RequiresRoles(value = {"1"})
    public ServerResponse deleteTeam(Integer teamId) {
        int effNum = 0;
        try {
            effNum = teamService.removeTeam(teamId);
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(400, "删除失败");
        }
        if (effNum == 0) {
            return ServerResponse.createByErrorCodeMessage(400, "删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }


    @ApiOperation("获取团体信息")
    @GetMapping("/getTeam")
    @RequiresRoles(value = {"1"})
    public ServerResponse getTeam(Team teamCondition) {

        Team team = teamService.getTeamByCondition(teamCondition);
        TeamGetDto teamGetDto=new TeamGetDto();
        teamGetDto.setTeam(team);
        //查出已经是领队了的UserID
        List<Integer> list= teamService.getLeaderId();

        //查询所有用户  中  不是领队的用户  并且属于当前团队的用户
        List<UserAndTeamDto> userAndTeamDtos= userService.getNotLeader(list,teamCondition.getTeamId());
        //加上当前团队的领队用户  如果没选择就不加
        UserAndTeamDto user=new UserAndTeamDto();
        user.setUserId(-1);
        if(team.getLeaderId()!=-1){
            User byId = userService.getById(team.getLeaderId());
            user.setUserId(byId.getUserId());
            user.setNickname(byId.getNickname());
            userAndTeamDtos.add(user);
        }
        teamGetDto.setUser(user);
        teamGetDto.setUserList(userAndTeamDtos);
        if (team != null) {
            return ServerResponse.createBySuccess(teamGetDto);
        }
        return ServerResponse.createByErrorMessage("查询不到该团体信息");
    }

    @ApiOperation("修改团体")
    @PutMapping("/editTeam")
    @RequiresRoles(value = {"1"})
    public ServerResponse editTeam(@RequestBody Team team) {
        if (team == null || team.getTeamName() == null) {
            return ServerResponse.createByErrorCodeMessage(400, "修改失败，团体信息为空");
        }

        if (teamService.getTeamByName(team.getTeamName()) > 1) {
            return ServerResponse.createByErrorCodeMessage(400, "修改失败，团体名称已存在");
        }

        team.setEditTime(LocalDateTime.now());
        int effNum = 0;
        try {
            effNum = teamService.modifyTeam(team);
        } catch (Exception e) {
            return ServerResponse.createByErrorCodeMessage(400, "修改失败");
        }
        if (effNum == 0) {
            return ServerResponse.createByErrorCodeMessage(400, "修改失败");
        }
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @ApiOperation("注册时获取所有团体")
    @GetMapping("/getAllTeams")
    public ServerResponse getAllTeams(){
        List<Team> teams= teamService.getAllTeams();
        return ServerResponse.createBySuccess(teams);
    }
}

