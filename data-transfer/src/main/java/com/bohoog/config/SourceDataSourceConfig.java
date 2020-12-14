package com.bohoog.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 配置来源数据库数据源
 * @author Liangxp
 * @date 2019/11/20 21:03
 */
@Configuration
@MapperScan(basePackages = "com.bohoog.mapper.source", sqlSessionFactoryRef = "sourceSqlSessionFactory")
public class SourceDataSourceConfig {

    /**
     * 配置数据源
     * @return 数据源
     */
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties("spring.datasource.source")
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置session工厂
     * @param datasource 数据源
     * @return session工厂
     * @throws Exception mapper文件路径配置异常
     */
    @Bean(name = "sourceSqlSessionFactory")
    public SqlSessionFactory sourceSqlSessionFactory(@Qualifier("sourceDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sourceMapper/**/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource 数据源
     * @return 事物管理器
     */
    @Bean(name="sourceTransactionManager")
    public DataSourceTransactionManager sourceTransactionManager(@Qualifier("sourceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置sessiontemplate
     * @param sessionfactory session工厂
     * @return SqlSessionTemplate
     */
    @Bean(name = "sourceSqlSessionTemplate")
    public SqlSessionTemplate sourceSqlSessionTemplate(@Qualifier("sourceSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
