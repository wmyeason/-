package com.ashuo.scms.dto;

import com.ashuo.scms.entity.Season;
import com.ashuo.scms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleDto implements Serializable {
    private Long id;

    private User user;

    private Season season;

    private String content;

    private LocalDateTime create_time;
}
