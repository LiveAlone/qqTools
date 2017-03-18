package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class BooleanRandom implements JavaValueRandom<Boolean>{

    public Boolean random(Boolean random){
        return randomDefault();
    }

    public static Boolean randomDefault(){
        return ThreadLocalRandom.current().nextBoolean();
    }
}
