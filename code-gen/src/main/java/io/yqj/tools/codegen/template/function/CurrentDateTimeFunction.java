package io.yqj.tools.codegen.template.function;


import com.google.common.collect.Lists;
import com.mitchellbosecke.pebble.extension.Function;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-05-21
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
public class CurrentDateTimeFunction implements Function {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public Object execute(Map<String, Object> args) {
        return DateTime.now().toString(DATE_TIME_FORMATTER) + "\n";
    }

    @Override
    public List<String> getArgumentNames() {
        return Lists.newArrayList("now");
    }
}
