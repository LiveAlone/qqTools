package io.yqj.tools.codegen.template;

import com.google.common.collect.ImmutableMap;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import io.yqj.tools.codegen.template.filter.EscapeJoinFilter;
import io.yqj.tools.codegen.template.filter.ForeachIJoinFilter;
import io.yqj.tools.codegen.template.filter.WrapJoinPropertyFilter;
import io.yqj.tools.codegen.template.function.CurrentDateTimeFunction;
import io.yqj.tools.codegen.template.function.WrapPropertyFunction;

import java.util.Map;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-15
 */
class SqlMapperExtension extends AbstractExtension{

    @Override
    public Map<String, Filter> getFilters() {
        return ImmutableMap.of(
                "wrapJoin", (Filter)new WrapJoinPropertyFilter(),
                "escapeJoin", (Filter)new EscapeJoinFilter(),
                "foreachIJoin", new ForeachIJoinFilter());
    }

    @Override
    public Map<String, Function> getFunctions() {
        return ImmutableMap.of("wrap", (Function)new WrapPropertyFunction(),
                "now", new CurrentDateTimeFunction());
    }
}
