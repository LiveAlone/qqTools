package io.yqj.tools.codegen.util;

import com.google.common.collect.Lists;
import com.google.common.primitives.Primitives;
import io.yqj.tools.codegen.model.Field;
import org.springframework.util.ReflectionUtils;

import java.util.Date;
import java.util.List;

import static java.lang.reflect.Modifier.isStatic;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe: 解析Class 字段信息生成对应的Field
 */
public class ClassFieldAnalyse {

    public static List<Field> classFields(Class clazz){
        final List<Field> fields = Lists.newArrayList();
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(java.lang.reflect.Field field) throws IllegalArgumentException, IllegalAccessException {
                if(isStatic(field.getModifiers())){
                    return;
                }
                if(ignoreClass(field.getType())){
                    return;
                }
                fields.add(Field.fromClassType(field.getName(), field.getType(), "todo"));
            }
        });
        return fields;
    }

    public static boolean ignoreClass(Class type){
        return !(Primitives.isWrapperType(type)
                || Primitives.allPrimitiveTypes().contains(type)
                || type == String.class
                || type == Date.class);
    }
}
