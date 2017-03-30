package io.yqj.tools.codegen.randomvalue;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by yaoqijun on 2017/3/30.
 * random class format 格式化
 */
public class RandomFormatter {

    private static DateTimeFormatter TIME = DateTimeFormat.forPattern("YYYY-MM-DD HH:mm:ss");

    public static String formatter(Object o){
        if (o instanceof Date){
            return dateFormatter(o);
        }else {
            return toStringFormatter(o);
        }
    }

    private static String toStringFormatter(Object o){
        return o.toString();
    }

    private static String dateFormatter(Object o){
        if (o instanceof Date){
            return new DateTime(o).toString(TIME);
        }
        return o.toString();
    }
}
