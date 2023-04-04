
#代码生成器
## 微服务代码生成

### 项目介绍

> - 后端代码生成
> - 前端代码生成
> - 前端代码样式选择 弹窗/页面

### 开发环境及工具
1. [Git 2.29.2](https://repo.huaweicloud.com/git-for-windows/) 或更高版本
2. JDK 1.8 ,例如 [OpenJDK](https://repo.huaweicloud.com/openjdk/)
3. [Apache Maven](https://maven.apache.org/download.cgi) 3.5.0 或更高版本
4. `IDEA 2019`或更高版本

### 安装介绍
1. 在 `IDEA` 中导入整个项目
2. 按照需求修改 `src-> resources`下的 `application.properties`
3. 点击run 或 debug :即可生成代码

### 项目结构
```
java.com.hope                                 
│  ├── config                                 公共配置类
│  ├── controller.databaseUtil                数据库信息工具
│  ├── core
│  │  ├── exception                           异常处理
│  │  ├── model                                
│  │  │  ├── ClassInfo                        表信息
│  │  │  ├── FieldInfo                        字段信息
│  │  ├── util
│  │  │  ├── StringUtil                       字符串工具 
│  │  │  ├── TableParseUtil                   数据库表语句转换工具
│  │  │ CodeGeneratorTool                     代码生成工具
│  ├── model
│  │  ├── CodeConfig                          配置类
│  │  ├── returnT                             返回类
│  ├── service          
│  │  ├── codeService                         代码生成实现
│  ├── util                                   FreeMarker 工具类
│  ├── HopeGeneratorApplication               主入口
│   
├── resource
│  ├──templates
│  │  ├── xxl-code-generator                  后端文件模板存放
│  │  ├── xxl-code-generator-front            前端文件模板存放
└──  README.md                                项目介绍文档
```

### 配置介绍
```properties
# mysql数据源
code.generate.jdbcUrl=
# db账号
code.generate.username=
# db密码
code.generate.password=
# 生成模板代码的根路径（最好指定脚手架生成的项目的跟路径）(后端)
code.generate.projectRootPath=
# 生成模板代码的根路径(前端)
code.generate.projectRootPathFront=
# 必填字段: 生成模板代码的项目名（对应脚手架的artifactId）
code.generate.projectName=
# 必填字段: 生成模板代码的包名（对应脚手架的package）
code.generate.projectPackage=
# 必填字段:前端微服务模块对应的微服务路由
code.generate.microRoute=
# 所需剔除的表名前缀
code.generate.tbPrefix=
# 必须字段：ALL:所有表都生成,指定需要生成的表：table1,table2,table3
code.generate.filterTableNames=
# 非必须字段，指定需要排除的表：table1,table2,table3
code.generate.excludeTableNames=
# 必须字段: 生成代码选择前端/后端 1前端 2后端 3 全部
code.generate.sideChose=
# 必须字段: 前端的代码风格  1 弹窗 2 页面
code.generate.frontEndStyle=
```


###注意事项

1. 后端代码id字段需根据实际情况更改生成方式, 默认为mybatisPlus的雪花算法.
2. 前端模块需要在用户中心配置, 验证也是生成的, 根据需要调整.
