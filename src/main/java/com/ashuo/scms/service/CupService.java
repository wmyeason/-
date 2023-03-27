package com.ashuo.scms.service;

import com.ashuo.scms.entity.Cup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (Cup)表服务接口
 *
 * @author makejava
 * @since 2023-03-23 22:16:34
 */
public interface CupService extends IService<Cup>{

    boolean saveTeam(Cup winner);
}
