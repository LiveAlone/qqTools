package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class DoubleRandom implements JavaValueRandom<Double>{
    public Double random(Double range){
        return ThreadLocalRandom.current().nextDouble(range);
    }
}
