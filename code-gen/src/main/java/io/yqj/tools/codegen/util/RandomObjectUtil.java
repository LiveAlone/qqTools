package io.yqj.tools.codegen.util;

import com.google.common.collect.Lists;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.RandomListObject;
import io.yqj.tools.codegen.model.RandomObject;
import io.yqj.tools.codegen.randomvalue.RandomObjectManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class RandomObjectUtil {

    // 随机生成 Object
    public static List<RandomObject> toRandomObject(List<Field> fields, int size){
        List<RandomObject> result = new ArrayList<>(size);
        RandomObjectManager randomObjectManager = new RandomObjectManager();
        for (int i=0; i<size; i++){
            RandomObject randomObject = new RandomObject(fields.size());
            for (Field field : fields) {
                randomObject.put(field.getFieldName(), randomObjectManager.randomValue(field.getClassType()));
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
                randomFields.add(field.getFieldName(), randomObjectManager.randomValue(field.getClassType()));
            }
            result.add(randomFields);
        }
        return result;
    }
}
