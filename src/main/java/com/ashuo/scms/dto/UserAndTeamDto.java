package com.ashuo.scms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain =  true)
public class UserAndTeamDto  implements Serializable {
    private Integer userId;
    private String nickname;
}
