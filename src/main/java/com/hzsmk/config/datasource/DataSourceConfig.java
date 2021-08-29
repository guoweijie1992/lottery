package com.hzsmk.config.datasource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import com.hzsmk.common.base.Consts;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author jiangjiaheng
 */
@Configuration
@MapperScan(basePackages = "com.hzsmk.lottery.dao", sqlSessionTemplateRef = Consts.PRIMARY_SQLSESSION_TEMPLATE)
public class DataSourceConfig {

    /**
     * 定义主数据源
     *
     * @return
     */
    @Bean(name = Consts.PRIMARY_DATASOURCE)
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = Consts.PRIMARY_SQLSESSION_FACTORY)
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier(Consts.PRIMARY_DATASOURCE) DataSource dataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        factory.setConfiguration(configuration);

        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // id使用雪花算法生成long型
        dbConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDbConfig(dbConfig);
        globalConfig.setBanner(false);
        factory.setGlobalConfig(globalConfig);

        //分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        interceptor.setProperties(properties);
        factory.setPlugins(new Interceptor[]{interceptor});

        return factory.getObject();
    }

    /**
     * 配置声明式事务管理器
     */
    @Bean(name = Consts.PRIMARY_TRANSACTION_MANAGER)
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier(Consts.PRIMARY_DATASOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = Consts.PRIMARY_SQLSESSION_TEMPLATE)
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier(Consts.PRIMARY_SQLSESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
