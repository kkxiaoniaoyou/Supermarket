package com.cbz.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbz.example.entity.ThirdPartyLog;
import com.cbz.example.mapper.ThirdPartyLogMapper;
import com.cbz.example.service.ThirdPartyLogService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【sys_third_party_log(第三方接口调用记录)】的数据库操作Service实现
 * @createDate 2024-01-10 23:17:37
 */
@Service
public class ThirdPartyLogServiceImpl extends ServiceImpl<ThirdPartyLogMapper, ThirdPartyLog>
        implements ThirdPartyLogService {

}




