package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class ShortRandom implements JavaValueRandom<Short>{

    public Short random(Short range){
        return (short)ThreadLocalRandom.current().nextInt(range);
    }

}
