package com.ashuo.scms.dto;

import com.ashuo.scms.entity.Team;
import com.ashuo.scms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TeamGetDto  implements Serializable {
    //团队信息
    private Team team;

    //已选择的领队信息
    private UserAndTeamDto user;

    //可以设置为领队的用户信息
    private List<UserAndTeamDto> userList;
}
