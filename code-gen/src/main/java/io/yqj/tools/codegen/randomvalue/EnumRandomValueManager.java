package io.yqj.tools.codegen.randomvalue;

import io.yqj.tools.codegen.MyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun on 2017/4/5.
 */
@Component
public class EnumRandomValueManager {

    private static final String SPLIT_DOT = ",";

    @Autowired
    private MyConfiguration myConfiguration;

    private Map<String, List<String>> keyValues = new HashMap<>();

    @PostConstruct
    public void init(){
        for (String s : myConfiguration.getEnums()) {
             String[] strings = s.split(SPLIT_DOT);
            if (strings == null || strings.length < 2){
                throw new RuntimeException("yml config error");
            }
            List<String> configEnum = Arrays.asList(strings);
            keyValues.put(configEnum.get(0), configEnum.subList(1, configEnum.size()));
        }
    }

    public Boolean isEnum(String key){
        return keyValues.containsKey(key);
    }

    public String randomEnum(String key){
        List<String> values = keyValues.get(key);
        return values.get(IntegerRandom.localRandom(values.size()));
    }
}
