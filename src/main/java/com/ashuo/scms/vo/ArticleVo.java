package com.ashuo.scms.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo implements Serializable {

    private String content;

    private Integer  s_id;

    private Integer  u_id;
}
