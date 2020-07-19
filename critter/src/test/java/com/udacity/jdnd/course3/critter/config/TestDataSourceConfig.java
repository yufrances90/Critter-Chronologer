package com.udacity.jdnd.course3.critter.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class TestDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSourceForTest(DataSourceProperties properties) {

        DataSourceBuilder dsb = DataSourceBuilder.create();

        dsb.url(properties.getUrl());
        dsb.driverClassName(properties.getDriverClassName());
        dsb.password(properties.getPassword());
        dsb.username(properties.getUsername());

        return dsb.build();
    }
}

