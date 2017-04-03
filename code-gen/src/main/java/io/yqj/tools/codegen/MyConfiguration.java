package io.yqj.tools.codegen;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

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
