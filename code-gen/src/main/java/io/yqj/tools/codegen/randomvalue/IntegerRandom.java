package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class IntegerRandom implements JavaValueRandom<Integer>{
    public Integer random(Integer range){
        return IntegerRandom.localRandom(range);
    }

    public static Integer localRandom(Integer range){
        return ThreadLocalRandom.current().nextInt(range);
    }
}
