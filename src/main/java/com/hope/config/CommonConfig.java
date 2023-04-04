package com.hope.config;

import com.hope.controller.DatabaseUtil;
import com.hope.model.CodeConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * @author puritykid
 * @description: 公共配置类
 * @date 2021/9/30 13:59
 */
@ConditionalOnClass(CodeConfig.class)
@Configuration
public class CommonConfig {

    private static final Logger log = LoggerFactory.getLogger(CommonConfig.class);

    @PostConstruct
    public void setDataSource() {

        DatabaseUtil.setDataSource(dataSource());
        log.info("数据源设置成功！");

    }

    @Autowired
    private CodeConfig codeConfig;

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(codeConfig.getJdbcUrl());
        hikariConfig.setUsername(codeConfig.getUsername());
        hikariConfig.setPassword(codeConfig.getPassword());
        return new HikariDataSource(hikariConfig);
    }
}
