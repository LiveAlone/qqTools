
## Tools
这是一个工具类的集合, 当前包含如下模块:
    轻轻家教对应的代码模板的设计模式配置方式。
    ```
    TODO Handler Enum 对应的配置方式。
    ```

### code-gen （代码生成模块的使用方式）
这是通过java对象自动生成对应sqlMapper及schema的功能

使用方式:

* application.yml 配置对应的数据源
    ```
        配置对应的 datasource , ignoreField 配置
    ```

* 进入 code-gen 目录内, 指定tableName 配置方式 
    ```
        mvn spring-boot:run -Drun.arguments='tableName1, tableName2 .....'  
    ```


> 2017.3.18 添加生成对应的测试代码的配置模板, Bean 数据的随机生成的配置方式。
> 添加 Bean 数据的随机生成配制方式

#### 随机生成Test Bean 的配置方式
随机生成 Bean 生成对应的 Class 校验对应的 CRUD 配置使用的方法。

#### TODO List Map 对应的类型的格式化方式

### V1.0.0
    1. 基础的文件生成方式， class mapper , test 生成配置方式

#### 添加配置文件的修改方式 （V2.0.0）
```$xslt
1. Handler 基础字段的映射郭方式
2. 配置文件指定 isDeleted, create_time, last_update_time 对应的配置方式。
3. Enum 字段的指定方式， 字段随机生成的配置方式。
4. List 字段的配置生成方式， name 名称。 测试文件的配置生成方式
5. 基础配置字段生成方式。
6. 数据读取字段的方式，删除 Java Class 配置方式
```

配置文件的设计方式：
1. Handler 基础配置方式， 通用字段Field, 特殊字段的Field 对应的配置方式。



















