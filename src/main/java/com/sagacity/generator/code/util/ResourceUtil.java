/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sagacity.generator.code.util;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sagacity.generator.code.bean.CodeConfigInfo;

/**
 * 资源读取类
 * @author xingyun
 * @date 2021-04-15 10:11
 */
public final class ResourceUtil {

    /**
     * 读取配置方法
     * @return 配置信息
     */
    public static CodeConfigInfo getConfig(String configPath){
        CodeConfigInfo configInfo = new CodeConfigInfo();
        Props props = PropsUtil.get(configPath);
        configInfo.setUrl(props.getStr("datasource.url"));
        configInfo.setDriverName(props.getStr("datasource.driverName"));
        configInfo.setUsername(props.getStr("datasource.username"));
        configInfo.setPassword(props.getStr("datasource.password"));
        String includeStr = props.getStr("strategyConfig.include");
        if(StringUtils.isNotBlank(includeStr)){
            String[] include = includeStr.split(",");
            configInfo.getStrategyConfig().setInclude(include);
        }
        configInfo.getStrategyConfig().setEntityLombokModel(props.getBool("strategyConfig.entityLombokModel",true));
        // 作者
        configInfo.getGlobalConfig().setAuthor(props.getStr("globalConfig.author","yangchangkui"));
        // 输出目录
        configInfo.getGlobalConfig().setOutputDir(props.getStr("globalConfig.outputDir","yangchangkui"));
        return configInfo;
    }
}
