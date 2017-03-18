package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class LongRandom implements JavaValueRandom<Long>{
    public Long random(Long range){
        return randomDefault(range);
    }

    public static Long randomDefault(Long range){
        return ThreadLocalRandom.current().nextLong(range);
    }
}
