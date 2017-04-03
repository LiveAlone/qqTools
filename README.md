
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

### 随机生成Test Bean 的配置方式
随机生成 Bean 生成对应的 Class 校验对应的 CRUD 配置使用的方法。

### TODO List Map 对应的类型的格式化方式