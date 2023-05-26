package com.wmy.scms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageDto implements Serializable {
    private List<ArticleDto> records;

    private Long total;

    private Long size;

    private Long current;


}
