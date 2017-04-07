package io.yqj.tools.codegen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by yaoqijun on 2017/3/30.
 */
@Configuration
@ConfigurationProperties("local")
public class MyConfiguration {

    private String databaseName;

    private List<String> enums;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<String> getEnums() {
        return enums;
    }

    public void setEnums(List<String> enums) {
        this.enums = enums;
    }
}
