package com.keepstudy.service.impl;

import com.keepstudy.config.GenerateConfig;
import com.keepstudy.domain.FieldInfo;
import com.keepstudy.domain.TableInfo;
import com.keepstudy.generate.GenerateStrategy;
import com.keepstudy.service.DatabaseService;
import com.keepstudy.utils.JavaType;
import com.keepstudy.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseServiceImpl implements DatabaseService {

    private final GenerateConfig config;
    private Connection connection;

    public DatabaseServiceImpl(GenerateConfig config) {
        this.config = config;
    }

    @Override
    public void generate() throws SQLException, ClassNotFoundException, IOException {
        this.connect();
        try {
            List<TableInfo> tableInfoList = this.getTableInfo();

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            String templatePath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("templates")).getPath();
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            for (TableInfo tableInfo : tableInfoList) {
                System.out.println("deal table: " + tableInfo.getDbName());
                for (GenerateStrategy generateStrategy : config.getGenerateStrategyList()) {
                    generateStrategy.createTemplate(config, tableInfo, cfg);
                }
            }
        } finally {
            this.close();
        }
    }

    public List<TableInfo> getTableInfo() throws SQLException {
        DatabaseMetaData metaData = this.connection.getMetaData();
        ResultSet resultSet = metaData.getTables(this.connection.getCatalog(), null, "user", new String[]{"TABLE"});

        List<TableInfo> tableInfoList = new ArrayList<>();
        while (resultSet.next()) {
            // Table Message
            TableInfo tableInfo = new TableInfo();
            tableInfo.setDbName(resultSet.getString("TABLE_NAME"));
            tableInfo.setDbRemark(resultSet.getString("REMARKS"));
            tableInfo.setJavaName_fwl(StringUtils.javaName(tableInfo.getDbName()));
            tableInfo.setJavaName(StringUtils.toFirstUp(tableInfo.getJavaName_fwl()));

            ResultSet primaryKeys = metaData.getPrimaryKeys(this.connection.getCatalog(), null, tableInfo.getDbName());
            String primaryKeyName = null;
            if (primaryKeys.next()) {
                primaryKeyName = primaryKeys.getString("COLUMN_NAME");
            }
            if (primaryKeyName == null) {
                System.out.println("表格：" + tableInfo.getDbName() + ", 查询主键失败！");
                continue;
            }
            tableInfoList.add(tableInfo);

            // Query Field Message
            String fieldNameSql = String.format("SELECT * FROM %s LIMIT 1", tableInfo.getDbName());
            PreparedStatement fieldNameStatement = connection.prepareStatement(fieldNameSql);
            ResultSet fieldNameResult = fieldNameStatement.executeQuery();
            // Query Field Remark
            String fieldBaseSql = String.format("SELECT column_name, column_comment FROM information_schema.columns WHERE table_name = '%s'", tableInfo.getDbName());
            PreparedStatement fieldBaseStatement = connection.prepareStatement(fieldBaseSql);
            ResultSet fieldBaseResult = fieldBaseStatement.executeQuery();
            Map<String, String> tableNameAndRemark = new HashMap<>();
            while (fieldBaseResult.next()) {
                tableNameAndRemark.put(fieldBaseResult.getString(1), fieldBaseResult.getString(2));
            }
            // Field Message
            List<FieldInfo> fieldInfoList = new ArrayList<>();
            ResultSetMetaData fieldNameMetaData = fieldNameResult.getMetaData();
            for (int i = 1; i <= fieldNameMetaData.getColumnCount(); i++) {
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setDbName(fieldNameMetaData.getColumnName(i));
                fieldInfo.setDbType(JDBCType.valueOf(fieldNameMetaData.getColumnType(i)).getName());
                fieldInfo.setDbRemark(tableNameAndRemark.get(fieldInfo.getDbName()));
                fieldInfo.setJavaName(StringUtils.javaName(fieldInfo.getDbName()));
                fieldInfo.setJavaName_fwc(StringUtils.toFirstUp(fieldInfo.getJavaName()));
                fieldInfo.setJavaType(JavaType.getJavaType(fieldNameMetaData.getColumnType(i)));
                fieldInfoList.add(fieldInfo);

                if (primaryKeyName.equals(fieldInfo.getDbName())) {
                    tableInfo.setPrimary(fieldInfo);
                }
            }
            tableInfo.setFieldInfoList(fieldInfoList);
        }
        return tableInfoList;
    }

    public synchronized void connect() throws ClassNotFoundException, SQLException {
        System.out.println("connect database...");
        if (this.connection != null && !this.connection.isClosed()) {
            return;
        }
        Class.forName(config.getDriver());
        this.connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
    }

    public synchronized void close() throws SQLException {
        System.out.println("close database...");
        if (this.connection == null || this.connection.isClosed()) {
            return;
        }
        this.connection.close();
    }

}
