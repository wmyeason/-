package com.wmy.scms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("菜单信息")
@NoArgsConstructor
@AllArgsConstructor
public class Mainmenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mainmenu_id", type = IdType.AUTO)
    private Integer mainmenuId;

    private String title;

    private String path;

    private Integer type;

}
