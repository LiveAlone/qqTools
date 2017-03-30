package io.yqj.tools.codegen.randomvalue;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class RandomObjectManager {

    private static final Logger logger = LoggerFactory.getLogger(RandomObjectManager.class);

    public static final Double defaultDoubleRangle = 1000D;

    public static final Integer defaultIntegerRange = 1000;

    public static final Long defaultLongRange = 1000L;

    public static final Short defaultShortRange = 1000;

    public static final String defaultStringlength = "10";

    private Map<Class, RandomManager> randomManagerMap;

    private Map<Class, Object> randomDefaultValue ;

    public RandomObjectManager() {
        randomManagerMap = new HashMap<>(5);
        randomDefaultValue = new HashMap<>(5);

        randomManagerMap.put(Long.class, RandomManager.<Long>newInstance());
        randomDefaultValue.put(Long.class, defaultLongRange);

        randomManagerMap.put(Integer.class, RandomManager.<Integer>newInstance());
        randomDefaultValue.put(Integer.class, defaultIntegerRange);

        randomManagerMap.put(Short.class, RandomManager.<Short>newInstance());
        randomDefaultValue.put(Short.class, defaultShortRange);

        randomManagerMap.put(Double.class, RandomManager.<Double>newInstance());
        randomDefaultValue.put(Double.class, defaultDoubleRangle);

        randomManagerMap.put(String.class, RandomManager.<String>newInstance());
        randomDefaultValue.put(String.class, defaultStringlength);

        randomManagerMap.put(Date.class, RandomManager.<Date>newInstance());
    }

    public <T> String randomValue(Class<T> clz){

        if (randomManagerMap.containsKey(clz)){
            return randomManagerMap.get(clz).randomValue(getDefaultValue(clz));
        }else {
            logger.error("random class not found, clz is :{}", clz.getSimpleName());
            throw new IllegalStateException("randomClass.not.found");
        }
    }

    private <T> Object getDefaultValue(Class<T> clz){
        if (Objects.equals(Date.class, clz)){
            return DateTime.now().plusDays(1).toDate();
        }
        return randomDefaultValue.get(clz);
    }
}
