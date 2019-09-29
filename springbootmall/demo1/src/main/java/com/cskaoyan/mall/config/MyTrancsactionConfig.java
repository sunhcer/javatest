package com.cskaoyan.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class MyTrancsactionConfig {
    //注册druid数据源事务管理器
    //先测试一下没有用代理类包裹的话,sb会不会自动包裹service层的操作
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
//    @Bean
//    public TransactionProxyFactoryBean serviceProxy(UserService userService, DataSourceTransactionManager dataSourceTransactionManager){
//        TransactionProxyFactoryBean transactionProxyFactoryBean=
//                new TransactionProxyFactoryBean();
//        transactionProxyFactoryBean.setTarget(userService);
//        transactionProxyFactoryBean.setProxyInterfaces(new Class[]{UserService.class});
//        transactionProxyFactoryBean.setTransactionManager(dataSourceTransactionManager);
//        Properties transactionAttributes=
//                new Properties();
//        transactionAttributes.setProperty("transfer","PROPAGATION_REQUIRED,ISOLATION_DEFAULT");
//        transactionProxyFactoryBean.setTransactionAttributes(transactionAttributes);
//        return transactionProxyFactoryBean;
//    }
}
