# App
中小企业后管和门户综合系统

## 准备工作
### 1. 安装jdk（1.8+）
本项目使用的java开发的，安装过程不在此展开，安装后配置环境变量。

### 2. 安装maven
本项目使用的maven进行管理的，安装过程不在此展开，安装后配置环境变量。

### 3. 安装MySQL
安装过程不在此展开，安装成功后，将用户名、密码和数据库名配置到`application-dev.yml`中，prod环境类似。

### 4. 安装redis服务器
安装过程不在此展开，安装成功后，将redis相关要素配置到`app.properties`中。

## 快速开始
### 1. 修改配置
- 修改`log4j2.xml`中的日志路径
- 修改`app.properties`中的文件上传路径

### 2. 打包
```
mvn clean install
```

### 3. 运行
```
java -jar app-web/target/app-web-*.jar
```

## 技术栈
1. Spring
2. Spring Boot
3. Spring Batch
4. Mybatis
5. PageHelper
6. Mybatis Generator
7. Redis
8. Freemarker
9. jQuery
10. Html
11. Css
12. JavaScript
13. Bootstrap

