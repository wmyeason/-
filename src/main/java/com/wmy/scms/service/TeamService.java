package com.wmy.scms.service;

import com.wmy.scms.entity.Team;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-03-31
 */

public interface TeamService {
    IPage<Team> getAllTeam(Page<Team> page, Team team);

    Team getTeamByCondition(Team team);

    int addTeam(Team team);

    int modifyTeam(Team team);

    int removeTeam(int teamId);

    List<Integer> getLeaderId();

    Integer getTeamByName(String team);

    String getNameById(Integer tId);

    List<Team> getAllTeams();
}

