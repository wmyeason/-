package com.wmy.scms.service.impl;

import com.wmy.scms.entity.Cup;
import com.wmy.scms.mapper.CupMapper;
import com.wmy.scms.service.CupService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * (Cup)表服务实现类
 *
 * @author makejava
 * @since 2023-03-23 22:16:34
 */
@Service
@Slf4j
public class CupServiceImpl extends ServiceImpl<CupMapper, Cup> implements CupService {

    @Override
    public boolean saveTeam(Cup winner) {
        return this.save(winner);
    }
}
