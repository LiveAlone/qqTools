package io.yqj.tools.codegen.randomvalue;

import io.yqj.tools.codegen.MyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yaoqijun on 2017/4/5.
 */
@Component
public class EnumRandomValueManager {

    private static final String SPLIT_DOT = ",";

    @Autowired
    private MyConfiguration myConfiguration;

    private Map<String, List<XmlClassRandomValue>> specialFields;

    @PostConstruct
    public void init(){
        Map<String, Map<String,String>> configSpecialField = myConfiguration.getFieldValues();
        if (configSpecialField == null || configSpecialField.size() == 0){
            specialFields = new HashMap<>(0);
            return;
        }
        specialFields = configSpecialField.entrySet().stream()
                .collect(Collectors.toMap(k->k.getKey(),
                        v->v.getValue().entrySet().stream()
                                .map(s->new XmlClassRandomValue(s.getKey(),
                                        s.getValue())).collect(Collectors.toList())));
    }

    public Boolean isEnum(String key){
        return specialFields.containsKey(key);
    }

    // 返回对应的Key Value 的映射关系
    public XmlClassRandomValue randomEnum(String key){
        List<XmlClassRandomValue> xmlClassRandomValues = specialFields.get(key);
        return xmlClassRandomValues.get(IntegerRandom.localRandom(xmlClassRandomValues.size()));
    }

    // 对应Xml 中的Value ， Class 对应的 Value 映射关系
    public static class XmlClassRandomValue{

        private String xmlValue;

        private String classValue;

        public XmlClassRandomValue(String xmlValue, String classValue) {
            this.xmlValue = xmlValue;
            this.classValue = classValue;
        }

        public XmlClassRandomValue() {
        }

        public String getXmlValue() {
            return xmlValue;
        }

        public void setXmlValue(String xmlValue) {
            this.xmlValue = xmlValue;
        }

        public String getClassValue() {
            return classValue;
        }

        public void setClassValue(String classValue) {
            this.classValue = classValue;
        }
    }
}
