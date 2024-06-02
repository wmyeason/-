package com.wmy.scms.mapper;

import com.wmy.scms.entity.Team;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 *
 * @since 2023-03-31
 */
@Mapper
public interface TeamMapper {
    //增加Team
    int insertTeam(Team team);

    //修改Team
    int updateTeam(Team team);

    //删除Team
    int deleteTeam(int teamId);

    //按条件查询Team
    IPage<Team> queryTeamByTeamName(Page<Team> page, @Param("team") Team team);


    Team queryTeamByTeamCondition(@Param("team") Team team);

    List<Integer> getLeaderId();

    Integer getTeamByName(String team);

    String getNameById(Integer tId);

    List<Team> getAllTeams();
}
