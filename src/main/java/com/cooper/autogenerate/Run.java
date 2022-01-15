package com.cooper.autogenerate;

import com.alibaba.fastjson.JSONObject;
import com.cooper.autogenerate.config.GenerateConfig;
import com.cooper.autogenerate.service.DatabaseService;
import com.cooper.autogenerate.service.impl.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Run {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        JSONObject subData = new JSONObject();
        subData.put("baseMapper", "com.cooper.autogenerate.base.mapper.BaseMapper");
        subData.put("baseService", "com.cooper.autogenerate.base.service.BaseService");
        subData.put("baseServiceImpl", "com.cooper.autogenerate.base.service.impl.BaseServiceImpl");
        GenerateConfig generateConfig = new GenerateConfig.DatabaseConfigBuilder()
                .url("jdbc:mysql://127.0.0.1:3306/user")
                .user("root")
                .password("123456")
                .generatePackage("com.cooper.db")
                .addDefaultGenerate("domain", ".java", "domain.ftl")
                .addDefaultGenerate("service", "Service.java", "service.ftl", subData)
                .addDefaultGenerate("service.impl", "ServiceImpl.java", "serviceImpl.ftl", subData)
                .addDefaultGenerate("dao", "Mapper.java", "dao.ftl", subData)
                .addDefaultGenerate("mapper", "Mapper.xml", "mapper.ftl")
                .addDefaultGenerate("mapper", "ExtMapper.xml", "mapperExt.ftl")
                .addDefaultGenerate("controller", "Controller.java", "controller.ftl", subData)
                .dirByType()
                .build();
        DatabaseService databaseService = new DatabaseServiceImpl(generateConfig);
        databaseService.generate();
    }

}
