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
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="io.esteban.kim.skeleton.springboot.mybatis.dao.writerdb", sqlSessionFactoryRef="writerSqlSessionFactory")
@EnableTransactionManagement
public class WriterDatabaseConfig {
    @Bean(name ="writerDataSource")
    @Primary
    @ConfigurationProperties(prefix ="spring.datasource.hikari.writerdb")
    public DataSource writerDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name ="writerSqlSessionFactory")
    @Primary
    public SqlSessionFactory writerSqlSessionFactory(@Qualifier("writerDataSource") DataSource writerDataSource, ApplicationContext applicationContext)throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(writerDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name ="writerSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate writerSqlSessionTemplate(SqlSessionFactory writerSqlSessionFactory)throws Exception {

        return new SqlSessionTemplate(writerSqlSessionFactory);
    }
}
