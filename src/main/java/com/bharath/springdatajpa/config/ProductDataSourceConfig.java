package com.bharath.springdatajpa.config;

import com.bharath.springdatajpa.product.entities.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.boot.SchemaAutoTooling;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.H2Dialect;
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
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"com.bharath.springdatajpa.product"})
public class ProductDataSourceConfig {

    @Bean(name = "productDataSourceProperties")
    @ConfigurationProperties("spring.datasource-product")
    public DataSourceProperties productDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "productDatasource")
    public DataSource productDataSource() {
        return productDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        HashMap<String, String> productJpaProperties = new HashMap<>();
        //productJpaProperties.put(AvailableSettings.HBM2DDL_AUTO, SchemaAutoTooling.CREATE.name().toLowerCase());
        //productJpaProperties.put(AvailableSettings.DIALECT, H2Dialect.class.getName());
        return builder.dataSource(productDataSource()).packages(Product.class)
                .persistenceUnit("productDataSource")
                .properties(productJpaProperties)
                .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
}
