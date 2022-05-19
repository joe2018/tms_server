package org.myfun.tms_server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.myfun.tme_server.mapper")
public class MybatisPlusConfig {
}
