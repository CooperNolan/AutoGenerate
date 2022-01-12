package com.cooper.autogenerate.config;

import com.alibaba.fastjson.JSONObject;
import com.cooper.autogenerate.generate.GenerateStrategy;
import com.cooper.autogenerate.generate.impl.BaseGenerateStrategy;

import java.util.ArrayList;
import java.util.List;

public class GenerateConfig {

    private String driver;
    private String url;
    private String user;
    private String password;
    private String generatePackage;
    private int dirType;

    private List<GenerateStrategy> generateStrategyList;

    private GenerateConfig() {
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getGeneratePackage() {
        return generatePackage;
    }

    public int getDirType() {
        return dirType;
    }

    public List<? extends GenerateStrategy> getGenerateStrategyList() {
        return generateStrategyList;
    }

    public static class DatabaseConfigBuilder {

        GenerateConfig generateConfig = new GenerateConfig();

        public DatabaseConfigBuilder() {
            this.generateConfig.driver = "com.mysql.cj.jdbc.Driver";
            this.generateConfig.dirType = 1;
            this.generateConfig.generatePackage = "com.cooper.db";
            this.generateConfig.generateStrategyList = new ArrayList<>();
        }

        public DatabaseConfigBuilder driver(String driver) {
            this.generateConfig.driver = driver;
            return this;
        }

        public DatabaseConfigBuilder url(String url) {
            this.generateConfig.url = url;
            return this;
        }

        public DatabaseConfigBuilder user(String user) {
            this.generateConfig.user = user;
            return this;
        }

        public DatabaseConfigBuilder password(String password) {
            this.generateConfig.password = password;
            return this;
        }

        public DatabaseConfigBuilder generatePackage(String generatePackage) {
            this.generateConfig.generatePackage = generatePackage;
            return this;
        }

        public DatabaseConfigBuilder dirByType() {
            this.generateConfig.dirType = 1;
            return this;
        }

        public DatabaseConfigBuilder dirByTable() {
            this.generateConfig.dirType = 2;
            return this;
        }

        public DatabaseConfigBuilder addDefaultGenerate(String packageName, String fileSuffix, String templateName) {
            return addDefaultGenerate(packageName, fileSuffix, templateName, new JSONObject());
        }

        public DatabaseConfigBuilder addDefaultGenerate(String packageName, String fileSuffix, String templateName, JSONObject supData) {
            GenerateStrategy generateStrategy = new BaseGenerateStrategy(packageName, fileSuffix, templateName, supData);
            this.generateConfig.generateStrategyList.add(generateStrategy);
            return this;
        }

        public DatabaseConfigBuilder addGenerate(GenerateStrategy generateStrategy) {
            this.generateConfig.generateStrategyList.add(generateStrategy);
            return this;
        }

        public GenerateConfig build() {
            return this.generateConfig;
        }
    }
}
