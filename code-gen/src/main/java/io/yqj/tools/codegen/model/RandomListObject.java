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
    public void add(String key, String xmlValueString, String javaValueString, String fieldMethodName){
        this.add(new RandomField(key, xmlValueString, javaValueString, fieldMethodName));
    }

    public static class RandomField{

        public RandomField(String key, String xmlValueString, String javaValueString, String fieldMethodName) {
            this.key = key;
            this.xmlValueString = xmlValueString;
            this.javaValueString = javaValueString;
            this.fieldMethodName = fieldMethodName;
        }

        public RandomField() {
        }

        String key;

        String xmlValueString;  // xml 中数据 Value 数据的显示方式

        String javaValueString; // java 数据中的 String 显示方式

        private String fieldMethodName;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getXmlValueString() {
            return xmlValueString;
        }

        public void setXmlValueString(String xmlValueString) {
            this.xmlValueString = xmlValueString;
        }

        public String getJavaValueString() {
            return javaValueString;
        }

        public void setJavaValueString(String javaValueString) {
            this.javaValueString = javaValueString;
        }

        public String getFieldMethodName() {
            return fieldMethodName;
        }

        public void setFieldMethodName(String fieldMethodName) {
            this.fieldMethodName = fieldMethodName;
        }

        @Override
        public String toString() {
            return "RandomField{" +
                    "key='" + key + '\'' +
                    ", xmlValueString='" + xmlValueString + '\'' +
                    ", javaValueString='" + javaValueString + '\'' +
                    ", fieldMethodName='" + fieldMethodName + '\'' +
                    '}';
        }
    }

}
