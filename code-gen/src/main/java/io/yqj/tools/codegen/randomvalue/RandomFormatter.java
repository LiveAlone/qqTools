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

    public static String formatterJavaType(Object o){
        if (o instanceof Date){
            return dateJavaFormatter((Date) o);
        }else {
            return toStringFormatter(o);
        }
    }

    public static String formatterXmlType(Object o){
        if (o instanceof Date){
            return dateXmlFormatter((Date) o);
        }else if (o instanceof String){
            return stringXmlFormatter(o);
        }else {
            return toStringFormatter(o);
        }
    }

    private static String toStringFormatter(Object o){
        return o.toString();
    }

    private static String dateXmlFormatter(Date o){
        return String.format(datatimeFormatterString, new DateTime(o).toString(TIME));
    }

    private static String dateJavaFormatter(Date o){
        return new DateTime(o).toString(TIME);
    }

    private static String stringXmlFormatter(Object o){
        if (o instanceof String){
            return "\"" + o.toString() + "\"";
        }
        return o.toString();
    }
}
