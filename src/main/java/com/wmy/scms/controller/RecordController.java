package com.wmy.scms.controller;


import com.wmy.scms.common.lang.ServerResponse;
import com.wmy.scms.entity.Athlete;
import com.wmy.scms.entity.QueryInfo;
import com.wmy.scms.entity.Record;
import com.wmy.scms.entity.User;
import com.wmy.scms.service.RecordService;
import com.wmy.scms.util.ObjectUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 *
 * @since 2023-04-05
 */
@Api(tags = "项目记录接口")
@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @ApiOperation("查询项目记录列表")
    @GetMapping("/queryRecord")
    @RequiresAuthentication
    public ServerResponse queryRecord(QueryInfo queryInfo, Record record) {
        Athlete athlete = ObjectUtils.isNull(record.getAthlete()) ? new Athlete() : record.getAthlete();
        User user = ObjectUtils.isNull(athlete.getUser()) ? new User() : athlete.getUser();

        user.setNickname(queryInfo.getQuery());
        athlete.setUser(user);
        record.setAthlete(athlete);

        //分页查询
        Page<Record> page = new Page<>(queryInfo.getCurrentPage(), queryInfo.getPageSize());
        IPage<Record> recordList = recordService.getRecordByRecordCondition(page, record);
        return ServerResponse.createBySuccess(recordList);
    }


}
