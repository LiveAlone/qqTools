package io.yqj.tools.codegen.model;

import com.google.common.collect.Lists;
import io.yqj.tools.codegen.util.StringConvert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-05-21
 * Email:yaoqj@terminus.io
 * Descirbe: 当前实体类的Demon模式内容设计方式.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingleClass implements Serializable{

    private String className;

    private String tableName;

    private String lowClassName; // xml 生成内容Mapper 数据内容

    private String singleComment;   //对应的数据表注解信息

    private List<Field> fields;

    public static SingleClass buildByClassName(String className, String singleComment, List<Field> fields){
        return SingleClass.builder()
                .className(className)
                .tableName(StringConvert.beanClassNameToTableName(className))
                .lowClassName(StringConvert.beanClassNametoLowClassName(className))
                .fields(fields)
                .singleComment(singleComment)
                .build();
    }

    public static SingleClass buildByTableName(String tableName, String singleComment, List<Field> fields){
        return SingleClass.builder()
                .className(StringConvert.tableNameToClassName(tableName))
                .tableName(tableName)
                .lowClassName(StringConvert.tableNameToMapperName(tableName))
                .singleComment(singleComment)
                .fields(fields)
                .build();
    }

    public List<String> queryFieldName(){
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            result.add(field.getFieldName());
        }
        return result;
    }

    public List<String> queryFieldType(){
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            result.add(field.getFieldType());
        }
        return result;
    }

    public List<String> queryColumnName(){
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            result.add(field.getColumnName());
        }
        return result;
    }

    public List<String> queryColumnType(){
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            result.add(field.getColumnType());
        }
        return result;
    }

    @Override
    public String toString() {
        return "SingleClass{" +
                "className='" + className + '\'' +
                ", tableName='" + tableName + '\'' +
                ", lowClassName='" + lowClassName + '\'' +
                ", singleComment='" + singleComment + '\'' +
                ", fields=" + fields +
                '}';
    }
}
