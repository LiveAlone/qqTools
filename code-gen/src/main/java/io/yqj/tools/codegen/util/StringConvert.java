package io.yqj.tools.codegen.util;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe: 定义字符串转换方式
 */
public class StringConvert {

    public static String beanClassNameToTableName(String className){
        return CaseFormatUtil.upCamelToLowSub(className) + "s";
    }

    public static String beanClassNametoLowClassName(String className){
        return CaseFormatUtil.upCamelToLowCamel(className);
    }

    /**
     * table name to Class name 去除 "t_"
     * @param tableName
     * @return
     */
    public static String tableNameToClassName(String tableName){
        return CaseFormatUtil.lowSubToUpCamel(tableName.substring(2));
    }

    /**
     * table 转换成 ClassNameMapper 配置方式
     * @param tableName
     * @return
     */
    public static String tableNameToMapperName(String tableName){
        return CaseFormatUtil.lowSubToLowCamel(tableName.substring(2));
    }

    public static String tableColumnToMethodName(String column){
        return CaseFormatUtil.lowSubToUpCamel(column);
    }

    public static String tableColumnToClassField(String column){
        return CaseFormatUtil.lowSubToLowCamel(column);
    }

    public static String classFieldToTableColumn(String field){
        return CaseFormatUtil.lowCamelToLowSub(field);
    }

    public static String classFieldToClassMethod(String field){
        return CaseFormatUtil.lowCamelToUpCamel(field);
    }
}
