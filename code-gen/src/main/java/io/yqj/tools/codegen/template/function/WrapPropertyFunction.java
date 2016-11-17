package io.yqj.tools.codegen.template.function;

import com.google.common.collect.ImmutableList;
import com.mitchellbosecke.pebble.extension.Function;

import java.util.List;
import java.util.Map;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-15
 */
public class WrapPropertyFunction implements Function {
    @Override
    public Object execute(Map<String, Object> args) {
        String propertyName = (String) args.get("propertyName");
        return "#{" + propertyName + "}";
    }

    @Override
    public List<String> getArgumentNames() {
        return ImmutableList.of("propertyName");
    }
}
