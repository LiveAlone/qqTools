package io.yqj.tools.codegen.template.filter;

import com.mitchellbosecke.pebble.extension.Filter;
import io.yqj.tools.codegen.model.Field;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yaoqijun.
 * Date:2016-04-23
 * Email:yaoqj@terminus.io
 * Descirbe: mapper foreach 循环内容包裹方式
 */
public class ForeachIJoinFilter implements Filter {
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

            builder.append("#{i.").append(entry).append(" jdbcType=")
                    .append(fields.get(i).getColumnMapperType()).append("}");
            isFirst = false;
        }
        return builder.toString();
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
