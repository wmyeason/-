package com.wmy.scms.dto;

import com.wmy.scms.entity.Season;
import com.wmy.scms.entity.User;
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
    private String id;

    private User user;

    private Season season;

    private String content;

    private LocalDateTime create_time;
}
