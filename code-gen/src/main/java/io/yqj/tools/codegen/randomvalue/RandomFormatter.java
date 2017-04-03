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

    private static final String datatimeFormatterString = "JodaTimeUtil.stringToDate(\"%s\", JodaTimeUtil.DEFAULT_TIME_FORMAT)";

    public static String formatter(Object o){
        if (o instanceof Date){
            return dateFormatter(o);
        }else if (o instanceof String){
            return stringToFormatter(o);
        }else {
            return toStringFormatter(o);
        }
    }

    private static String toStringFormatter(Object o){
        return o.toString();
    }

    private static String dateFormatter(Object o){
        if (o instanceof Date){
            return String.format(datatimeFormatterString, new DateTime(o).toString(TIME));
        }
        return o.toString();
    }

    private static String stringToFormatter(Object o){
        if (o instanceof String){
            return "\"" + o.toString() + "\"";
        }
        return o.toString();
    }
}
