package com.cbz.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbz.example.entity.ThirdPartyLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @description 针对表【sys_third_party_log(第三方接口调用记录)】的数据库操作Mapper
 * @createDate 2024-01-10 23:17:37
 * @Entity com.cbz.example.entity.ThirdPartyLog
 */
@Mapper
public interface ThirdPartyLogMapper extends BaseMapper<ThirdPartyLog> {

}




