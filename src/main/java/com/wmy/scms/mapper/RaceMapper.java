package com.wmy.scms.mapper;

import com.wmy.scms.entity.Race;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Race)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-21 12:42:31
 */
@Mapper
public interface RaceMapper extends BaseMapper<Race>{

  boolean updateList(@Param("race")List<Race> race);

  IPage<Race> getRaceByIPages(Page<Race> racePage,@Param("race") Race race);

  boolean updateRace(@Param("race") Race race);

  Integer getStartRace(int i);
}

