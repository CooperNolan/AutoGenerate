# AutoGenerate

一个轻量级数据库代码生成工具

```java
JSONObject subData = new JSONObject(); // 补充数据，在这里可以设置base文件包名
subData.put("baseMapper", "com.keepstudy.autogenerate.base.mapper.BaseMapper"); // Mapper 继承的基类
subData.put("baseService", "com.keepstudy.autogenerate.base.service.BaseService"); // Service 继承的基类
subData.put("baseServiceImpl", "com.keepstudy.autogenerate.base.service.impl.BaseServiceImpl"); // ServiceImpl继承的基类
// 配置信息
GenerateConfig generateConfig = new GenerateConfig.DatabaseConfigBuilder()
    .url("jdbc:mysql://127.0.0.1:3306/user")
    .user("root")
    .password("123456")
    .generatePackage("com.keepstudy.db") // 指定生成包名
    .addDefaultGenerate("domain", ".java", "domain.ftl") // 类型名，文件后缀， freemarker模板名
    .addDefaultGenerate("service", "Service.java", "service.ftl", subData) // 类型名，文件后缀， freemarker模板名， 补充数据（可在模板中使用）
    .addDefaultGenerate("service.impl", "ServiceImpl.java", "serviceImpl.ftl", subData)
    .addDefaultGenerate("dao", "Mapper.java", "dao.ftl", subData)
    .addDefaultGenerate("mapper", "Mapper.xml", "mapper.ftl")
    .addDefaultGenerate("mapper", "ExtMapper.xml", "mapperExt.ftl")
    .dirByType() // 创建目录方式；byType按照类型创建 byTable安装数据库表名创建
    .build();
DatabaseService databaseService = new DatabaseServiceImpl(generateConfig);
databaseService.generate(); // 开始生成
```