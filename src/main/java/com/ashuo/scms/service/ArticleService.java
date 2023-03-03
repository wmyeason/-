package com.ashuo.scms.service;

import com.ashuo.scms.entity.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2023-02-09 16:32:15
 */
public interface ArticleService extends IService<Article>{

    IPage<Article> getArticleByPage(Page<Article> page);

    List<Article> getNewAr();
}
