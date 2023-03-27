package com.ashuo.scms.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (Reward)实体类
 *
 * @author makejava
 * @since 2023-03-20 20:38:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("reward")
public class Reward implements Serializable {
    /**
     * 主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 获奖队伍的ID
     */
    private Integer teamId;
    /**
     * 个人获奖ID
     */
    private Integer userId;
    /**
     * 图片存储的地址
     */
    @TableField("img_url")
    private String imgUrl;
    /**
     * 获奖时间
     */

    private Date createTime;



}

