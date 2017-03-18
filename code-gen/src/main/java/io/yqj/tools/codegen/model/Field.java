package io.yqj.tools.codegen.model;

import com.google.common.base.Strings;
import io.yqj.tools.codegen.util.FieldTypeConvert;
import io.yqj.tools.codegen.util.StringConvert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field implements Serializable{

    private static final long serialVersionUID = 3169142101958853798L;

    private String fieldName;

    private String fieldMethodName;

    private String fieldType;

    private String columnName;

    private String columnType;

    private String columnMapperType;

    private String fieldComment;    // 对应的该列的注解信息

    private Class classType;

    public static Field fromClassType(String fieldName, Class fieldType, String fieldComment){
        Field field = Field.builder()
                .fieldName(fieldName)
                .fieldMethodName(StringConvert.classFieldToClassMethod(fieldName))
                .fieldType(fieldType.getSimpleName())
                .columnName(StringConvert.classFieldToTableColumn(fieldName))
                .columnType(FieldTypeConvert.fieldTypeToColumnType(fieldType))
                .columnMapperType(FieldTypeConvert.fieldTypeToColumnMapperType(fieldType))
                .fieldComment(commentConvert(fieldComment)).build();

        field.setClassType(FieldTypeConvert.fieldTypeToClassType(field.getFieldType()));
        return field;
    }

    public static Field fromColumnType(String columnName, String columnType, String fieldComment){
          Field field = Field.builder()
                .fieldName(StringConvert.tableColumnToClassField(columnName))
                .fieldMethodName(StringConvert.tableColumnToMethodName(columnName))
                .fieldType(FieldTypeConvert.columnTypeToFieldType(columnType))
                .columnMapperType(FieldTypeConvert.columnTypeToFieldMapperType(columnType))
                .columnName(columnName).columnType(columnType)
                .fieldComment(commentConvert(fieldComment))
                .build();
          field.setClassType(FieldTypeConvert.fieldTypeToClassType(field.getFieldType()));
          return field;
    }

    private static String commentConvert(String comment){
        if(Strings.isNullOrEmpty(comment)){
            return null;
        }else {
            return comment + "\n";
        }
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldMethodName='" + fieldMethodName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnMapperType='" + columnMapperType + '\'' +
                ", fieldComment='" + fieldComment + '\'' +
                ", classType=" + classType +
                '}';
    }
}
