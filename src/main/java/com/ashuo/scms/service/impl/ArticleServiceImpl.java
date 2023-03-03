package com.ashuo.scms.service.impl;

import com.ashuo.scms.entity.Article;
import com.ashuo.scms.mapper.ArticleMapper;
import com.ashuo.scms.service.ArticleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2023-02-09 16:32:15
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public IPage<Article> getArticleByPage(Page<Article> page) {
        IPage<Article> ip = this.page(page);
        return ip;
    }

    @Override
    public List<Article> getNewAr() {
        LambdaQueryWrapper<Article> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Article::getCreateTime);
        wrapper.last(" Limit 5 ");
        List<Article> list = this.list(wrapper);
        return list;
    }
}
