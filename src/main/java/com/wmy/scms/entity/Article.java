package com.wmy.scms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("article")
public class Article  implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField(value = "u_id")
    private Integer uId;

    private String content;

    @TableField(value = "s_id")
    private Integer sId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;
}
