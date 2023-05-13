package com.snapp.spendtracker.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.snapp.spendtracker"})
@EnableJpaRepositories(basePackages = {"com.snapp.spendtracker.repository"})
@EnableTransactionManagement
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfig {

    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;
    private final LiquibaseProperties liquibaseProperties;

    public LiquibaseConfig(DataSource dataSource, ResourceLoader resourceLoader,
                           LiquibaseProperties liquibaseProperties) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
        this.liquibaseProperties = liquibaseProperties;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.yaml");
        liquibase.setResourceLoader(resourceLoader);
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        return liquibase;
    }
}
