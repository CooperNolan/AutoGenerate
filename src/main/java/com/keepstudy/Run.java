package com.keepstudy;

import com.alibaba.fastjson.JSONObject;
import com.keepstudy.config.GenerateConfig;
import com.keepstudy.service.DatabaseService;
import com.keepstudy.service.impl.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Run {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        JSONObject subData = new JSONObject();
        subData.put("baseMapper", "com.keepstudy.base.mapper.BaseMapper");
        subData.put("baseService", "com.keepstudy.base.service.BaseService");
        subData.put("baseServiceImpl", "com.keepstudy.base.service.impl.BaseServiceImpl");
        GenerateConfig generateConfig = new GenerateConfig.DatabaseConfigBuilder()
                .url("jdbc:mysql://127.0.0.1:3306/user")
                .user("root")
                .password("123456")
                .generatePackage("com.keepstudy")
                .addDefaultGenerate("domain", ".java", "domain.ftl")
                .addDefaultGenerate("service", "Service.java", "service.ftl", subData)
                .addDefaultGenerate("service.impl", "ServiceImpl.java", "serviceImpl.ftl", subData)
                .addDefaultGenerate("dao", "Mapper.java", "dao.ftl", subData)
                .addDefaultGenerate("mapper", "Mapper.xml", "mapper.ftl")
                .addDefaultGenerate("mapper", "ExtMapper.xml", "mapperExt.ftl")
                .dirByType()
                .build();
        DatabaseService databaseService = new DatabaseServiceImpl(generateConfig);
        databaseService.generate();
    }

}