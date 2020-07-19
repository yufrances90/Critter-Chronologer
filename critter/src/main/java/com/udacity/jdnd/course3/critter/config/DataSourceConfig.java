package com.udacity.jdnd.course3.critter.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("datasource.mysql")
    public DataSource getDataSource(DataSourceProperties properties) {

        DataSourceBuilder dsb = DataSourceBuilder.create();

        dsb.username("sa");
        dsb.password(this.securePasswordService());

        return dsb.build();
    }

    private String securePasswordService() {
        return "sa1234";
    }
}
