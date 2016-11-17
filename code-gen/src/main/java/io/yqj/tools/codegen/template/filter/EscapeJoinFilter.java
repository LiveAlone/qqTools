package io.yqj.tools.codegen.template.filter;

import com.mitchellbosecke.pebble.extension.Filter;
import io.yqj.tools.codegen.model.Field;

import java.util.List;
import java.util.Map;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-15
 */
public class EscapeJoinFilter implements Filter {
    @Override
    public Object apply(Object input, Map<String, Object> args) {
        if (input == null) {
            return null;
        }

        List<Field> fields = (List<Field>) input;

        StringBuilder builder = new StringBuilder();

        String glue = ",";
        boolean isFirst = true;

        // 默认跳过第一个字段信息
        for (int i=1; i<fields.size(); i++){
            Field currentField = fields.get(i);

            if(!isFirst){
                builder.append(glue);
            }

            builder.append("`").append(currentField.getColumnName()).append("`");

            isFirst = false;
        }
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
