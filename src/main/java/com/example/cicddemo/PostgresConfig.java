package com.example.cicddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class PostgresConfig extends DataSourceConfig {
    private static final Logger logger = LogManager.getLogger(PostgresConfig.class);
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String dataSourceURL;

    @Autowired
    private Environment env;

    @Bean
    @Primary

    public DataSource dataSource() throws SQLException {

        logger.debug("Driver " + driver);
        logger.debug("DataSourceURL: " + dataSourceURL);

        String dbUserName = env.getProperty("UUNAME");
        String dbPassword = env.getProperty("paassword");

        System.out.println(env.getProperty("PATH"));
        System.out.println(dbUserName);

        // String dbUserName = "newdbuser";
        // String dbPassword = "Sql@2020";

        return getDataSource(dataSourceURL, dbUserName, dbPassword, driver, "POSTGRES_POOL");
    }

    @DependsOn("jdbcTemplate")
    @Bean("namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) throws SQLException {
        logger.debug("jdbcTemplate: " + jdbcTemplate);
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

}
