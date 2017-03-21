package io.yqj.tools.codegen;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.util.concurrent.ExecutionError;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.yqj.tools.codegen.component.InspectFieldFromBeans;
import io.yqj.tools.codegen.component.InspectFieldFromDatasource;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.RandomObject;
import io.yqj.tools.codegen.model.SingleClass;
import io.yqj.tools.codegen.template.Pebbler;
import io.yqj.tools.codegen.util.RandomObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-04-18
 */
@Component
@Slf4j
public class SchemaGenerator implements CommandLineRunner {

    private final Pebbler pebbler;

    private final InspectFieldFromDatasource inspectFieldFromDatasource;

    private final InspectFieldFromBeans inspectFieldFromBeans;

    List<String> tables = null;

    @Autowired
    public SchemaGenerator(Pebbler pebbler,
                           InspectFieldFromDatasource inspectFieldFromDatasource,
                           InspectFieldFromBeans inspectFieldFromBeans) {
        this.pebbler = pebbler;
        this.inspectFieldFromDatasource = inspectFieldFromDatasource;
        this.inspectFieldFromBeans = inspectFieldFromBeans;
        tables = Lists.newArrayList();
    }

    public void run(String... args) throws Exception {

        System.out.println("start test condition");

        /**
         * 添加Table 表名称
         */
        if( args != null && args.length != 0){
            for (String arg : args) {
                tables.add(arg);
            }
        }

        // 获取对应的数据表的信息
        List<SingleClass> singleClasses = inspectFieldFromDatasource.queryInspectFields(tables);

        for (SingleClass singleClass : singleClasses) {
            fromDatasource(singleClass);
        }


        singleClasses.forEach(s -> fromDatasource(s));

        System.out.println("finish test condition");

    }

    private void fromDatasource(SingleClass singleClass) {
        //build context
        Map<String, Object> context = Maps.newHashMap();
        context.put("singleClass", singleClass);
        context.put("fieldNames",singleClass.queryFieldName());

        toClass(context, singleClass);

        toMapper(context, singleClass);

        toDao(context, singleClass);

        // TODO 对应的 Service 配置方式
//        toReadService(context, singleClass);

        // TODO  对应的 Service Impl 路径配置方式
//        toReadServiceImpl(context, singleClass);

        // TODO add
//        toTextXmlContent(singleClass);
    }

    // 生成测试 Dao
    private void toTestDao(){

    }

    // 生成测试Xml
    private void toTextXmlContent(SingleClass singleClass){
        // todo add
        List<RandomObject> randomObjects = RandomObjectUtil.toRandomObject(singleClass.getFields(), 2);
        System.out.println(randomObjects);
    }

    private void toMapper(Map<String,Object> context, SingleClass singleClass) {
        try {
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate mapperTemplate = pebbler.compile("mapper.peb");
            mapperTemplate.evaluate(stringWriter, context);
            File targetFile = new File("sql/" + singleClass.getClassName() + "Mapper.xml");
            Files.createParentDirs(targetFile);
            Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("to mapper error");
        }
    }

    private void toClass(Map<String,Object> context, SingleClass singleClass) {
        try {
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate mapperTemplate = pebbler.compile("classes.peb");
            mapperTemplate.evaluate(stringWriter, context);
            File targetFile = new File("javaCode/" + singleClass.getClassName() + ".java");
            Files.createParentDirs(targetFile);
            Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("to class convert error haha");
        }
    }

    private void toSchema(Map<String,Object> context) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("noUse/schema.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("schemaSql/" + context.get("tableName").toString() + "_schema.sql");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toDao(Map<String,Object> context, SingleClass singleClass) {
        try {
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate mapperTemplate = pebbler.compile("dao.peb");
            mapperTemplate.evaluate(stringWriter, context);
            File targetFile = new File("mapper/" + singleClass.getClassName() + "Mapper.java");
            Files.createParentDirs(targetFile);
            Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("to dao error");
        }
    }

    private void toReadService(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("noUse/readService.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("service/" + singleClass.getClassName() + "ReadService.java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }

    private void toReadServiceImpl(Map<String,Object> context, SingleClass singleClass) throws Exception{
        StringWriter stringWriter = new StringWriter(8196);
        PebbleTemplate mapperTemplate = pebbler.compile("noUse/readServiceImpl.peb");
        mapperTemplate.evaluate(stringWriter, context);
        File targetFile = new File("service/" + singleClass.getClassName() + "ReadServiceImpl.java");
        Files.createParentDirs(targetFile);
        Files.write(stringWriter.toString(), targetFile, Charsets.UTF_8);
    }
}
