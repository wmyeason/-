package com.wmy.scms.mapper;

import com.wmy.scms.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 *
 * @since 2023-04-10
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    //增加单个Record
    int insertRecord(Record record);

    //修改Record
    int updateRecord(Record record);

    //按条件查询Record
    IPage<Record> queryRecordByRecordCondition(Page<Record> page, @Param("record") Record record);


    int deleteRecoerdByAId(int athleteId);
}
