package io.yqj.tools.codegen.component;

import com.google.common.collect.Lists;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.SingleClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-04-22
 * Email:yaoqj@terminus.io
 * Descirbe: 获取InspectField 通过Datasource
 */
@Component
@Slf4j
public class InspectFieldFromDatasource {

    private final JdbcTemplate jdbcTemplate;

    private static final String ALL_TABLES = "SHOW TABLE STATUS;";

    @Value("#{'${ignoreFields}'.split(',')}")
    private List<String> ignoreFields;

    @Autowired
    public InspectFieldFromDatasource(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * get all inspect fields from data source
     * @param tables 用户定义生成对应的数据表
     * @return
     */
    public List<SingleClass> queryInspectFields(List<String> tables){
        List<SingleClass> singleClasses = Lists.newArrayList();

        // 获取对应的数据表信息
        List<Map<String,Object>> tableAll = jdbcTemplate.queryForList(ALL_TABLES);

        //默认Load 全部的数据表
        List<Map<String,Object>> targetTable = Lists.newArrayList();
        if(tables.size() == 0){
            targetTable = tableAll;
        }else {
            for (Map<String,Object> s : tableAll) {
                if(tables.contains(s.get("Name").toString()))
                {
                    targetTable.add(s);
                }
            }
        }

        // get Table info
        for (Map<String,Object> s : targetTable) {
            singleClasses.add(
                    SingleClass.buildByTableName(s.get("Name").toString(),
                            String.valueOf(s.get("Comment")),
                            queryInspectTableField(s.get("Name").toString())));
        }
        return singleClasses;
    }

    private List<Field> queryInspectTableField(String table){

        List<Map<String,Object>> queryResult = jdbcTemplate.queryForList("SHOW FULL COLUMNS FROM "+ table);

        log.info("table :{} query result :{}", table, queryResult);

        List<Field> fields = Lists.newArrayList();
        for (Map<String, Object> stringObjectMap : queryResult) {

            if (ignoreFields.contains(stringObjectMap.get("Field").toString())){
                continue;
            }

            fields.add(Field.fromColumnType(
                    String.valueOf(stringObjectMap.get("Field")),
                    String.valueOf(stringObjectMap.get("Type")),
                    String.valueOf(stringObjectMap.get("Comment"))));
        }
        return fields;
    }
}
