package io.yqj.tools.codegen.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Slf4j
public class FieldTypeConvert {

    public static Class fieldTypeToClassType(String fieldType){
        String fieldTypeLow = fieldType.toLowerCase();
        if (fieldTypeLow.contains("long")){
            return Long.class;
        }
        if (fieldTypeLow.contains("integer")){
            return Integer.class;
        }
        if (fieldTypeLow.contains("string")){
            return String.class;
        }
        if (fieldTypeLow.contains("date")){
            return Date.class;
        }
        if (fieldTypeLow.contains("double")){
            return Double.class;
        }
        log.error("field type to Class type error, fieldType:{}", fieldType);
        return null;
    }

    /**
     * sql 字段转换 Class 字段
     * @param fieldType
     * @return
     */
    public static String columnTypeToFieldType(String jdbcType){
        String lowJdbcType = jdbcType.toLowerCase();

        if(lowJdbcType.contains("bigint")){
            return "Long";
        }

        if (lowJdbcType.contains("int")){
            return "Integer";
        }

        if(lowJdbcType.contains("varchar") || lowJdbcType.contains("text") || lowJdbcType.contains("char")){
            return "String";
        }
        if(lowJdbcType.contains("date") || lowJdbcType.contains("timestamp")){
            return "Date";
        }
        if(lowJdbcType.contains("double") || lowJdbcType.contains("float")||lowJdbcType.contains("decimal")){
            return "Double";
        }
        log.error("jdbc type convert error !! jdbcType:{}", jdbcType);
        return null;
    }

    /**
     * sql 字段转换 Class 字段
     * @param fieldType
     * @return
     */
    public static String columnTypeToFieldMapperType(String jdbcType){
        String lowJdbcType = jdbcType.toLowerCase();

        if(lowJdbcType.contains("bigint")){
            return "BIGINT";
        }

        if (lowJdbcType.contains("int")){
            return "INTEGER";
        }

        if(lowJdbcType.contains("varchar") || lowJdbcType.contains("text")){
            return "VARCHAR";
        }
        if(lowJdbcType.contains("date") || lowJdbcType.contains("timestamp")){
            return "DATETIME";
        }
        if(lowJdbcType.contains("float")){
            return "FLOAT";
        }
        if(lowJdbcType.contains("double")) {
            return "DOUBLE";
        }
        log.error("jdbc type convert error !! jdbcType:{}", jdbcType);
        return null;
    }

    /**
     * class 字段 sql 字段类型
     * @param classType
     * @return
     */
    public static String fieldTypeToColumnMapperType(Class javaType){
        if(javaType == boolean.class || javaType == Boolean.class){
            return "TINYINT";
        }

        if(javaType == String.class){
            return "VARCHAR";
        }

        if(javaType == short.class || javaType == Short.class){
            return "SMALLINT";
        }

        if(javaType == int.class || javaType == Integer.class){
            return "INTEGER";   // default 11
        }

        if(javaType == long.class || javaType == Long.class){
            return "BIGINT";
        }

        if(javaType == float.class || javaType == Float.class){
            return "FLOAT";
        }

        if(javaType == double.class || javaType == Double.class){
            return "DOUBLE";
        }

        if(javaType == Date.class){
            return "DATETIME";
        }
        log.info("field type convert error, field type:{}", javaType.getSimpleName());
        return null;
    }

    /**
     * class 字段 sql 字段类型
     * @param classType
     * @return
     */
    public static String fieldTypeToColumnType(Class javaType){
        if(javaType == boolean.class || javaType == Boolean.class){
            return "TINYINT(1)";
        }

        if(javaType == String.class){
            return "varchar(512)";
        }

        if(javaType == short.class || javaType == Short.class){
            return "smallint(6)";
        }

        if(javaType == int.class || javaType == Integer.class){
            return "int";   // default 11
        }

        if(javaType == long.class || javaType == Long.class){
            return "bigint(20)";
        }

        if(javaType == float.class || javaType == Float.class){
            return "float";
        }

        if(javaType == double.class || javaType == Double.class){
            return "double";
        }

        if(javaType == Date.class){
            return "datetime";
        }
        log.info("field type convert error, field type:{}", javaType.getSimpleName());
        return null;
    }

}
