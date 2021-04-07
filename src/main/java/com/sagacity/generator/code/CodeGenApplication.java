package com.sagacity.generator.code;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenApplication {

    public static void main(String[] args) {
        // 数据源配置
        DataSourceConfig dbconfig = new DataSourceConfig();
        dbconfig.setUrl("jdbc:mysql://localhost:3306/code?serverTimezone=UTC&setUnicode=true&characterEncoding=utf8");
        dbconfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dbconfig.setUsername("root");
        dbconfig.setPassword("root");

        // 策略配置项
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setInclude("oms_order");
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setSuperControllerClass("com.sagacity.framework.web.controller.BaseController");

        TemplateConfig templateConfig = new TemplateConfig();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBaseResultMap(true);
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setAuthor("xingyun");
        globalConfig.setYear("2020");
        globalConfig.setSwagger2(true);
        globalConfig.setOutputDir("F:\\Users\\yang\\IdeaProjects\\sagacity\\src\\main\\java");

        PackageConfig packageConfig = new PackageConfig();
        ConfigBuilder config = new ConfigBuilder(packageConfig,dbconfig,strategyConfig,
                templateConfig,globalConfig);

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setConfig(config);

        autoGenerator.execute();

    }

}
