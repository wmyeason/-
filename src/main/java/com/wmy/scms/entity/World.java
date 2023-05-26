package com.wmy.scms.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (World)实体类
 *
 * @author makejava
 * @since 2023-02-20 19:39:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("world")
public class World implements Serializable {
    /**
     * 主键 但是只能有16条数据
     */
    private Integer id;
    /**
     * 比赛队伍的ID
     */
    private Integer tId;
    /**
     * 比赛队伍的名称
     */
    private String tName;
    /**
     * 队伍处于的阶段 
1表示16强
2表示8强
3表示4强
4表示决赛
     */
    private Integer stage;

    //是否报名   0 未   1是成功
    private Integer status;

}

