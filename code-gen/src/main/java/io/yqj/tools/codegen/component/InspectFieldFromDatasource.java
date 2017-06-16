package io.yqj.tools.codegen.component;

import com.google.common.collect.Lists;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.SingleClass;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yaoqijun.
 * Date:2016-04-22
 * Email:yaoqj@terminus.io
 * Descirbe: 获取InspectField 通过Datasource
 */
@Component
@Slf4j
public class InspectFieldFromDatasource {

    private static final Logger logger = LoggerFactory.getLogger(InspectFieldFromDatasource.class);

    private final JdbcTemplate jdbcTemplate;

    private static final String ALL_TABLES = "SHOW TABLE STATUS;";

    private static final String TABLE_NAME_FIELD = "Name";

    private static final String TABLE_COMMENT_FIELD = "Comment";

    private static final String TABLE_FIELD_FIELD = "Field";

    private static final String TABLE_TYPE_FIELD = "Type";

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
        List<DatabaseTable> databaseTables = showAllTables(tables);
        List<SingleClass> singleClasses = new ArrayList<>(databaseTables.size());

        databaseTables.forEach(s->
            singleClasses.add(
                    SingleClass.buildByTableName(
                            s.getTableName(),
                            s.getComment(),
                            queryInspectTableField(s.getTableName()))));
        return singleClasses;
    }

    private List<DatabaseTable> showAllTables(List<String> selectTables){
        // 获取对应的数据表信息
        List<Map<String,Object>> tableAll = jdbcTemplate.queryForList(ALL_TABLES);

        Boolean hasSelectTables = selectTables.size()!=0;

        logger.info("has select tables:{}, selectTables:{}", hasSelectTables, selectTables);

        return tableAll.stream()
                .filter(s -> !hasSelectTables || selectTables.contains(s.get(TABLE_NAME_FIELD).toString()))
                .map(s -> new DatabaseTable(s.get(TABLE_NAME_FIELD).toString(), s.get(TABLE_COMMENT_FIELD).toString()))
                .collect(Collectors.toList());
    }

    private List<Field> queryInspectTableField(String table){

        List<Map<String,Object>> queryResult = jdbcTemplate.queryForList("SHOW FULL COLUMNS FROM "+ table);

        log.info("table :{} query result :{}", table, queryResult);

        List<DatabaseField> databaseFields = queryResult.stream()
                .filter(s->!ignoreFields.contains(s.get(TABLE_FIELD_FIELD).toString()))
                .map(s->new DatabaseField(s.get(TABLE_FIELD_FIELD).toString(),
                        s.get(TABLE_TYPE_FIELD).toString(),
                        s.get(TABLE_COMMENT_FIELD).toString()))
                .collect(Collectors.toList());

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

        logger.info("table:{}, from source field:{}", table, databaseFields);

        return databaseFields.stream().map(s->Field.fromColumnType(s.getFieldName(), s.getFieldType(), s.getFieldComment()))
                .collect(Collectors.toList());
    }

    private static class DatabaseField{

        private String fieldName;

        private String fieldType;

        private String fieldComment;

        public DatabaseField() {
        }

        public DatabaseField(String fieldName, String fieldType, String fieldComment) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.fieldComment = fieldComment;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public String getFieldComment() {
            return fieldComment;
        }

        public void setFieldComment(String fieldComment) {
            this.fieldComment = fieldComment;
        }
    }

    private static class DatabaseTable{

        private String tableName;

        private String comment;

        public DatabaseTable() {
        }

        public DatabaseTable(String tableName, String comment) {
            this.tableName = tableName;
            this.comment = comment;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
