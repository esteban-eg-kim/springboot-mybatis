package io.esteban.kim.skeleton.springboot.mybatis.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="io.esteban.kim.skeleton.springboot.mybatis.dao.readerdb", sqlSessionFactoryRef="readerSqlSessionFactory")
@EnableTransactionManagement
public class ReaderDatabaseConfig {
    @Bean(name ="readerDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.hikari.readerdb")
    public DataSource readerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name ="readerSqlSessionFactory")
    public SqlSessionFactory readerSqlSessionFactory(@Qualifier("readerDataSource") DataSource readerDataSource, ApplicationContext applicationContext)throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(readerDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name ="readerSqlSessionTemplate")
    public SqlSessionTemplate readerSqlSessionTemplate(SqlSessionFactory readerSqlSessionFactory)throws Exception {

        return new SqlSessionTemplate(readerSqlSessionFactory);
    }

}
