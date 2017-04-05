package io.yqj.tools.codegen.util;

import com.google.common.collect.Lists;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.RandomListObject;
import io.yqj.tools.codegen.model.RandomObject;
import io.yqj.tools.codegen.randomvalue.EnumRandomValueManager;
import io.yqj.tools.codegen.randomvalue.RandomFormatter;
import io.yqj.tools.codegen.randomvalue.RandomObjectManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoqijun on 2017/3/18.
 */
@Component
public class RandomObjectUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static EnumRandomValueManager enumRandomValueManager;

    // 随机生成 Object
    public static List<RandomObject> toRandomObject(List<Field> fields, int size){
        List<RandomObject> result = new ArrayList<>(size);
        RandomObjectManager randomObjectManager = new RandomObjectManager();
        for (int i=0; i<size; i++){
            RandomObject randomObject = new RandomObject(fields.size());
            for (Field field : fields) {
                randomObject.put(field.getColumnName(), randomObjectManager.randomValue(field.getClassType()));
            }
            result.add(randomObject);
        }
        return result;
    }

    public static List<RandomListObject> toRandomListObject(List<Field> fields, int size){
        List<RandomListObject> result = new ArrayList<>(size);
        RandomObjectManager randomObjectManager = new RandomObjectManager();
        for (int i=0; i<size; i++){
            RandomListObject randomFields = new RandomListObject(fields.size());
            for (Field field : fields) {
                String javaFormatterType = null;
                String xmlFormatterType = null;
                if (enumRandomValueManager.isEnum(field.getColumnName())){
                    String value = enumRandomValueManager.randomEnum(field.getColumnName());
                    javaFormatterType = value;
                    xmlFormatterType = value;
                }else {
                    Object value = randomObjectManager.randomValue(field.getClassType());
                    javaFormatterType = RandomFormatter.formatterJavaType(value);
                    xmlFormatterType = RandomFormatter.formatterXmlType(value);
                }
                randomFields.add(field.getColumnName(),
                        javaFormatterType,
                        xmlFormatterType,
                        field.getFieldMethodName());
            }
            result.add(randomFields);
        }
        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        RandomObjectUtil.applicationContext = applicationContext;
        RandomObjectUtil.enumRandomValueManager = (EnumRandomValueManager) applicationContext.getBean("enumRandomValueManager");
    }
}
