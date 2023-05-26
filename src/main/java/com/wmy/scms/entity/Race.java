package com.wmy.scms.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (Race)实体类
 *
 * @author makejava
 * @since 2023-02-21 12:42:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("race")
public class Race implements Serializable {
    /**
     * 表是第几场比赛
1-8是16强
9-12是8强
13-14是4强
15是决赛
     */
    private Integer id;
    /**
     * 左边比赛队伍的ID
     */

    private Integer aId;

    private String aName;

    private String aScore;
    /**
     * 右边比赛队伍的Id
     */

    private Integer bId;

    private String bName;

    private String bScore;
    /**
     * 比赛结果   存储的是赢家的ID
     */

    private Integer result;

    @TableField(value = "overtime")
    private Integer overTime;//是否有点球大战
    /**
     * 比赛开始状态
-1表示未开始
1表示16强比赛中
     2表示8强比赛中
     3表示4强比赛中
     4表示决赛
     */
    private Integer status;



}

