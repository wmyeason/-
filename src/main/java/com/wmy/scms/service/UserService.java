package com.wmy.scms.service;

import com.wmy.scms.dto.UserAndTeamDto;
import com.wmy.scms.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 *
 * @since 2023-03-29
 */
public interface UserService extends IService<User> {

    IPage<User> getUserByCondition(Page<User> page, User user);

    int addUser(User user);

    int removeUser(int userId);

    int modifyUser(User user);

    boolean checkHasUser(User user);

    boolean toReSetPass(User user);

    List<UserAndTeamDto> getNotLeader(List<Integer> list, Integer teamId);
}
