package io.yqj.tools.codegen.randomvalue;

import java.util.Date;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class DateRandom implements JavaValueRandom<Date>{

    @Override
    public Date random(Date stopDate) {
        Long nowTime = new Date().getTime();
        Long range = stopDate.getTime() - nowTime;
        return new Date(nowTime + LongRandom.randomDefault(range));
    }
}
