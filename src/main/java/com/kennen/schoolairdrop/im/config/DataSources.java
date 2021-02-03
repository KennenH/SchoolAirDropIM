package com.kennen.schoolairdrop.im.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author kennen
 * @date 2021/1/29 18:44
 * <p>
 * 此处配置有问题可能会导致有关于jdbc无法连接的问题
 */
@Configuration
public class DataSources {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.seconddatasource")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
