package com.sleepwalker;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @package: com.sleepwalker
 * @className: Main
 * @author: SleepWalker
 * @description: mybatis-plus generator
 * @date: 12:07
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        //创建对象
        AutoGenerator autoGenerator = new AutoGenerator();

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/dormitory");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("166");
        autoGenerator.setDataSource(dataSourceConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //System.getProperty("user.dir") 根目录
        globalConfig.setOutputDir("D:\\Programme1\\Java_Code\\Project\\Dormitory\\SpringBootProject\\dormitoryms" + "/src/main/java");
        globalConfig.setAuthor("SleepWalker");
        globalConfig.setOpen(false); //是否把文件夹打开
        //去掉Service的I
        globalConfig.setServiceName("%sService"); //%s是类名
        autoGenerator.setGlobalConfig(globalConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        //设置包名
        packageConfig.setParent("com.sleepwalker");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //数据库名
        strategyConfig.setInclude("absent", "building", "dormitory", "dormitory_admin",
                "moveout", "student", "system_admin");
        //下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //添加Lombok
        strategyConfig.setEntityLombokModel(true);
        autoGenerator.setStrategy(strategyConfig);

        //启动
        autoGenerator.execute();
    }
}















