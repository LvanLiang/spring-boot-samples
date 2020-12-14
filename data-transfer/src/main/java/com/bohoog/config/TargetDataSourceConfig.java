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
 * 配置目标数据库数据源
 * @author Liangxp
 * @date 2019/11/20 21:10
 */
@Configuration
@MapperScan(basePackages = "com.bohoog.mapper.target", sqlSessionFactoryRef = "targetSqlSessionFactory")
public class TargetDataSourceConfig {

    /**
     * 配置数据源
     * @return 数据源
     */
    @Bean(name = "targetDataSource")
    @ConfigurationProperties("spring.datasource.target")
    public DataSource targetDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置session工厂
     * @param datasource 数据源
     * @return session工厂
     * @throws Exception mapper文件路径配置异常
     */
    @Bean(name = "targetSqlSessionFactory")
    public SqlSessionFactory sourceSqlSessionFactory(@Qualifier("targetDataSource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/targetMapper/**/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置事务管理器
     * @param dataSource 数据源
     * @return 事物管理器
     */
    @Bean(name="targetTransactionManager")
    public DataSourceTransactionManager targetTransactionManager(@Qualifier("targetDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置sessiontemplate
     * @param sessionfactory session工厂
     * @return SqlSessionTemplate
     */
    @Bean(name = "targetSqlSessionTemplate")
    public SqlSessionTemplate targetSqlSessionTemplate(@Qualifier("targetSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}
