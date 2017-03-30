package io.yqj.tools.codegen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaoqijun on 2017/3/30.
 */
@Configuration
public class MyConfiguration {

    @Value("${local.databaseName}")
    private String databaseName;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
