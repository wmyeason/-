package com.wmy.scms.controller;

import com.wmy.scms.common.lang.ServerResponse;
import com.wmy.scms.dto.ArticleDto;
import com.wmy.scms.dto.ArticlePageDto;
import com.wmy.scms.entity.*;
import com.wmy.scms.service.ArticleService;
import com.wmy.scms.service.SeasonService;
import com.wmy.scms.service.UserService;
import com.wmy.scms.vo.ArticleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2023-02-09 16:32:16
 */
@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
 
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeasonService seasonService;

    @ApiOperation("添加加油稿")
    @PostMapping("/addArticle")
    public ServerResponse addArticle(@RequestBody ArticleVo articleVo){
        Article article=new Article();

        article.setSId(articleVo.getS_id());
        article.setUId(articleVo.getU_id());
        article.setContent(articleVo.getContent());
        //设置当前时间
        article.setCreateTime(LocalDateTime.now());
        //将数据添加到表中
        articleService.save(article);


        return ServerResponse.createBySuccess();
    }

    @ApiOperation("获取最新的5个加油稿")
    @GetMapping("/getCarAr")
    public ServerResponse getAllar(){
        List<Article> records= articleService.getNewAr();
        List<ArticleDto> arDto = getArDto(records);
        return ServerResponse.createBySuccess(arDto);
    }

    @ApiOperation("分页获取加油稿")
    @GetMapping("/queryArticle")
    public ServerResponse queryItem(QueryInfo queryInfo){
        Page<Article> page=new Page<>(queryInfo.getCurrentPage(), queryInfo.getPageSize());
        IPage<Article> articleList = articleService.getArticleByPage(page);
        List<Article> records = articleList.getRecords();

        ArticlePageDto res=new ArticlePageDto();
        res.setCurrent(articleList.getCurrent());
        res.setSize(articleList.getSize());
        res.setTotal(articleList.getTotal());

        List<ArticleDto> dtoList = getArDto(records);

        res.setRecords(dtoList);
        return ServerResponse.createBySuccess(res);
    }

    @ApiOperation("删除加油稿")
    @DeleteMapping("/deleteArticle")
    public ServerResponse deleteArticle(String id){
        Long ids=Long.valueOf(id);
        boolean b = articleService.removeById(ids);
        if(b){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("失败");
        }

    }


    public List<ArticleDto> getArDto(List<Article> records){
        List<ArticleDto> dtoList=new ArrayList<>();
        for(Article a:records){
            ArticleDto dto=new ArticleDto();
            dto.setId(""+a.getId());
            User u = userService.getById(a.getUId());
            dto.setUser(u);
            Season s = seasonService.getById(a.getSId());
            if(Objects.isNull(s)){
                s=new Season();
                s.setSeasonName("所有运动会");
            }
            dto.setSeason(s);
            dto.setCreate_time(a.getCreateTime());
            dto.setContent(a.getContent());
            dtoList.add(dto);
        }
        return dtoList;
    }
}

