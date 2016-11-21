
## Tools
这是一个工具类的集合, 当前包含如下模块:


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
