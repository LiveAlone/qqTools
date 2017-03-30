package io.yqj.tools.codegen.model;

import java.util.ArrayList;

/**
 * Created by yaoqijun on 2017/3/30.
 * list 类型的 随机生成的 Object 类型
 */
public class RandomListObject extends ArrayList<RandomListObject.RandomField>{

    public RandomListObject(int initialCapacity) {
        super(initialCapacity);
    }

    // 添加 RandomListField
    public void add(String key, String value){
        this.add(new RandomField(key, value));
    }

    public static class RandomField{

        public RandomField(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public RandomField() {
        }

        String key;

        String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "RandomField{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

}
