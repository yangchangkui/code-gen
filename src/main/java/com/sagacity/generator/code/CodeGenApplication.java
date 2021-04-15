package com.sagacity.generator.code;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sagacity.generator.code.bean.CodeConfigInfo;
import com.sagacity.generator.code.util.ResourceUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class CodeGenApplication {

    public static void main(String[] args) {
        // 参数获取配置文件地址
        String configPath;
        if(args.length > 0){
            configPath = args[0];
            String property = System.getProperty("user.dir");
            configPath= property+ File.separator+configPath;
        }else{
            configPath = "classpath:config.properties";
        }
        // 读取配置文件
        CodeConfigInfo configInfo = ResourceUtil.getConfig(configPath);
        log.debug("配置信息：{}",configInfo);
        // 数据源配置
        DataSourceConfig dbconfig = new DataSourceConfig();
        dbconfig.setUrl(configInfo.getUrl());
        dbconfig.setDriverName(configInfo.getDriverName());
        dbconfig.setUsername(configInfo.getUsername());
        dbconfig.setPassword(configInfo.getPassword());

        // 策略配置项
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude(configInfo.getStrategyConfig().getInclude());
        strategyConfig.setEntityLombokModel(configInfo.getStrategyConfig().isEntityLombokModel());
        strategyConfig.setSuperControllerClass("com.sagacity.framework.api.controller.BaseController");

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBaseResultMap(true);
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setYear(String.valueOf(DateUtil.thisYear()));
        globalConfig.setSwagger2(true);
        globalConfig.setAuthor(configInfo.getGlobalConfig().getAuthor());
        globalConfig.setOutputDir(configInfo.getGlobalConfig().getOutputDir());

        TemplateConfig templateConfig = new TemplateConfig();
        PackageConfig packageConfig = new PackageConfig();
        ConfigBuilder config = new ConfigBuilder(packageConfig,dbconfig,strategyConfig,
                templateConfig,globalConfig);

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setConfig(config);
        // 执行
        autoGenerator.execute();

    }

}
