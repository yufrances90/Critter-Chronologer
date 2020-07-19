package com.udacity.jdnd.course3.critter.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
    public DataSource getDataSourceForTest(DataSourceProperties properties) {

        DataSourceBuilder dsb = DataSourceBuilder.create();

        dsb.url("jdbc:h2:mem:testdb");

        return dsb.build();
    }

    @Bean
    SessionFactory sessionFactoryBean(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(
                dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory lsfb) {
        HibernateTransactionManager mgr = new HibernateTransactionManager();
        mgr.setSessionFactory(lsfb);
        return mgr;
    }

    private String securePasswordService() {
        return "sa1234";
    }
}

