package com.wmy.scms.mapper;

import com.wmy.scms.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-09 16:32:16
 */
 @Mapper
public interface ArticleMapper extends BaseMapper<Article>{

    
}

