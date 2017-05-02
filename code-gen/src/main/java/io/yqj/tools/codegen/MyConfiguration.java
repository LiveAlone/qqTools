package io.yqj.tools.codegen;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun on 2017/3/30.
 */
@Configuration
@ConfigurationProperties("local")
public class MyConfiguration {

    private String databaseName;

    private Map<String, Map<String, String>> fieldValues;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public Map<String, Map<String, String>> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, Map<String, String>> fieldValues) {
        this.fieldValues = fieldValues;
    }
}


