package io.yqj.tools.codegen.randomvalue;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class RandomManager<T> {

    private static final Logger logger = LoggerFactory.getLogger(RandomManager.class);

    private static final Map<Class, JavaValueRandom> valueTypeMap = new HashMap<>(10);

    public static final Integer RANDOM_TIMES = 5;

    static {
        valueTypeMap.put(Boolean.class, new BooleanRandom());
        valueTypeMap.put(Character.class, new CharRandom());
        valueTypeMap.put(Double.class, new DoubleRandom());
        valueTypeMap.put(Integer.class, new IntegerRandom());
        valueTypeMap.put(Long.class, new LongRandom());
        valueTypeMap.put(Short.class, new ShortRandom());
        valueTypeMap.put(String.class, new StringRandom());
        valueTypeMap.put(Date.class, new DateRandom());
    }

    private List<T> randomValues;

    private Integer randomTimes;

    private RandomManager(Integer retryTime){
        this.randomTimes = retryTime;
        this.randomValues = Lists.newArrayList();
    }

    public static <T> RandomManager newInstance(){
        return new RandomManager<T>(RANDOM_TIMES);
    }

    public  String randomValue(T range){
        if (range == null){
            logger.error("range not allow null");
            throw new IllegalArgumentException("range argument null");
        }
        if (!valueTypeMap.containsKey(range.getClass())){
            logger.error("not known class type, class:{}", range.getClass().getName());
            throw new IllegalStateException("not known class type");
        }

        for (int i=1; i<randomTimes; i++){
            T value = (T)valueTypeMap.get(range.getClass()).random(range);
            if (!randomValues.contains(value)){
                return RandomFormatter.formatter(value);
            }
        }

        logger.error("random manager random fail, range:{}, currentValues:{}", range, randomValues);
        throw new IllegalArgumentException("random retry fail");
    }



}
