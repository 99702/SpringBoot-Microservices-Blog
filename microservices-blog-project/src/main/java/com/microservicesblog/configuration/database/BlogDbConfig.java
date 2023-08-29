package com.microservicesblog.configuration.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "microservicesblogdbEntityManagerFactory",
        basePackages = {
                "com.microservicesblog.databases.microservicesblogdb"
        },
        transactionManagerRef = "microservicesblogdbTransactionManager"
)
public class BlogDbConfig {

    @Bean(name = "microservicesblogdbDataSourceProperties")
    @ConfigurationProperties("microservicesblogdb.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "microservicesblogdbDataSource")
    @ConfigurationProperties("microservicesblogdb.datasource.configuration")
    public DataSource dataSource(@Qualifier("microservicesblogdbDataSourceProperties") DataSourceProperties microservicesblogdbDataSourceProperties) {
        return microservicesblogdbDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "microservicesblogdbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("microservicesblogdbDataSource") DataSource microservicesblogdbDataSource) {
        return builder
                .dataSource(microservicesblogdbDataSource)
                .packages("com.microservicesblog.databases.microservicesblogdb")
                .persistenceUnit("microservicesblogdb")
                .build();
    }

    @Bean(name = "microservicesblogdbTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("microservicesblogdbEntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        return new JpaTransactionManager(db1EntityManagerFactory);
    }

    }
