package com.cooper.autogenerate.generate.impl;

import com.alibaba.fastjson.JSONObject;
import com.cooper.autogenerate.config.GenerateConfig;
import com.cooper.autogenerate.domain.TableInfo;
import com.cooper.autogenerate.generate.GenerateStrategy;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseGenerateStrategy implements GenerateStrategy {

    private String packageName;
    private String fileSuffix;
    private String templateName;
    private JSONObject supData;

    public BaseGenerateStrategy() {
    }

    public BaseGenerateStrategy(String packageName, String fileSuffix, String templateName, JSONObject supData) {
        this.packageName = packageName;
        this.fileSuffix = fileSuffix;
        this.templateName = templateName;
        this.supData = supData;
    }

    @Override
    public void createTemplate(GenerateConfig config, TableInfo tableInfo, Configuration cfg) {
        Template template = null;
        try {
            template = cfg.getTemplate(this.templateName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        File generateDir = generateDir(config, tableInfo);

        File file = new File(generateDir, tableInfo.getJavaName() + this.fileSuffix);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("create file fail，path: " + file.getAbsolutePath());
                e.printStackTrace();
                return;
            }
        }
        System.out.println("create file：" + file.getName());

        JSONObject data = new JSONObject();
        data.putAll(JSONObject.parseObject(JSONObject.toJSONString(tableInfo)));
        data.put("package", config.getGeneratePackage() + "." + (config.getDirType() == 1 ? packageName : tableInfo.getJavaName()));
        data.put("datetime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        data.put("author", "AutoGenerate");
        data.put("generatePackage", config.getGeneratePackage());
        data.put("dirType", config.getDirType());
        data.put("packageName", this.packageName);
        if (this.supData != null && this.supData.size() > 0) {
            data.putAll(supData);
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            template.process(data, pw);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }

    }

    public final File generateDir(GenerateConfig config, TableInfo tableInfo) {
        String generatePathSb = "src/main/java/" +
                config.getGeneratePackage().replaceAll("\\.", "/") +
                "/" +
                (config.getDirType() == 1 ? packageName.replaceAll("\\.", "/") : tableInfo.getJavaName()) +
                "/";
        File generateDir = new File(generatePathSb);
        if (!generateDir.exists() || !generateDir.isDirectory()) {
            generateDir.mkdirs();
        }
        return generateDir;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public JSONObject getSupData() {
        return supData;
    }

    public void setSupData(JSONObject supData) {
        this.supData = supData;
    }
}
