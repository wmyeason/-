package com.ashuo.scms.controller;

import com.ashuo.scms.service.CupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * (Cup)表控制层
 *
 * @author makejava
 * @since 2023-03-23 22:16:35
 */
@Slf4j
@RestController
@RequestMapping("cup")
public class CupController {

    @Autowired
    private CupService cupService;


}

