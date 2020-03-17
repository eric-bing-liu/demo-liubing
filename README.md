# demo-highgo总体介绍
demo for highgo databse


# 
# demo-springboot2-highgo 
一个面向HighgGo-DB的demo样例程序。

基础：spring-boot 2.2.5 RELEASE

持久层：mybatis、PageHelper

数据库连接：postgresql、druid

controller.UserController中有增、删、改、查（详情）、查（分页）、事务测试。






# demo-springboot2-highgo-mybatis-generator

mybatis 代码生成样例程序

配置文件位置：src\main\resources\mybatisGenerator\

generatorConfig.properties 可以配置数据库连接内容

```properties
# highgo setting
jdbc_driver =com.highgo.jdbc.Driver
jdbc_url=jdbc:highgo://127.0.0.1:5866/highgo
jdbc_user=highgo
jdbc_password=highgo2020
```

generatorConfig.xml

生成文件位置

```xml
        <!-- 生成模型的包名和位置-->
        <javaModelGenerator targetPackage="com.sinosoft.codegenerator.model" targetProject="src/tempmybatis">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- 生成映射文件的包名和位置-->
        <sqlMapGenerator targetPackage="mapping" targetProject="src/tempmybatis">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!-- 生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.sinosoft.codegenerator.mapper" targetProject="src/tempmybatis">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
```

设置要生成的表

```xml
        <!-- 要生成的表 s_user是数据库中的表名或视图名-->
        <table tableName="s_user">
            <property name="useActualColumnNames" value="false" />
            <!-- 数据库表主键 -->
            <generatedKey column="id" sqlStatement="JDBC" identity="true" />
        </table>
```







# HighGo-DB-Driver
程序用到的驱动jar包：HighGo-DB、postgre-sql