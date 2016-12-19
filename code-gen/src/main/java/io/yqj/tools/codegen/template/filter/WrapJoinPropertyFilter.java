package io.yqj.tools.codegen.template.filter;

import com.mitchellbosecke.pebble.extension.Filter;
import io.yqj.tools.codegen.model.Field;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-15
 */
public class WrapJoinPropertyFilter implements Filter {

    @Override
    public Object apply(Object input, Map<String, Object> args) {
        if (input == null) {
            return null;
        }

        List<Field> fields = (List<Field>) input;
        StringBuilder builder = new StringBuilder();
        String glue = ",";
        boolean isFirst = true;

        for(int i=0; i<fields.size(); i++){
            String entry = fields.get(i).getFieldName();
            if(!isFirst){
                builder.append(glue);
            }

            builder.append("#{").append(entry).append(", jdbcType=")
                    .append(fields.get(i).getColumnMapperType()).append("}");

            isFirst = false;
        }

        // add 0, now() 对应的 isDelete createTime
        builder.append(glue).append("0").append(glue).append("now()");
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
