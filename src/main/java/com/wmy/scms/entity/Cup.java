package com.wmy.scms.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * (Cup)实体类
 *
 * @author makejava
 * @since 2023-03-23 22:16:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cup")
public class Cup implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private Integer teamId;
    
    private String teamName;
    /**
     * 冠军还是亚军   2亚军  1 是冠军
     */
    private Integer type;



}

