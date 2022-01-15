package com.nx.day12.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author: wep
 * @Since: 2021/5/15 14:57
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy=true) // 开启aop的注解
@EnableTransactionManagement
@ComponentScan("com.nx.day12")
public class SpringConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl("jdbc:h2:mem:user");
//        dataSourceProperties.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSourceProperties.setUrl("jdbc:mysql://localhost:3308/test");
//        dataSourceProperties.setUsername("root");
//        dataSourceProperties.setPassword("wep1234");
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
