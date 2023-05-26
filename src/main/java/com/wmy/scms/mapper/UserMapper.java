package com.wmy.scms.mapper;

import com.wmy.scms.dto.UserAndTeamDto;
import com.wmy.scms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 *
 * @since 2023-03-29
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<User> queryUserByUserCondition(Page<User> page, @Param("user") User user);

    int insertUser(User user);

    int deleteUser(int userId);

    int updateUser(@Param("user") User user);

    List<UserAndTeamDto> getNotLeader(@Param("list") List<Integer> list,@Param("teamId") Integer teamId);
}
