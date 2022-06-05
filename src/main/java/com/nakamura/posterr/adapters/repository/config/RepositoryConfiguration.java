package com.nakamura.posterr.adapters.repository.config;

import com.nakamura.posterr.adapters.repository.FollowingRepository;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class RepositoryConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource driverManagerDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public Jdbi jdbi(DataSource dataSource) {

        final var jdbi = Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new PostgresPlugin());
        return jdbi;

    }

    @Bean
    public FollowingRepository followingRepository(Jdbi jdbi) {
        return jdbi.onDemand(FollowingRepository.class);
    }

}
