package io.yqj.tools.codegen;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import io.yqj.tools.codegen.component.InspectFieldFromDatasource;
import io.yqj.tools.codegen.model.Field;
import io.yqj.tools.codegen.model.RandomListObject;
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

    private final MyConfiguration myConfiguration;

    List<String> tables = null;

    @Autowired
    public SchemaGenerator(Pebbler pebbler, MyConfiguration myConfiguration,
                           InspectFieldFromDatasource inspectFieldFromDatasource) {
        this.pebbler = pebbler;
        this.inspectFieldFromDatasource = inspectFieldFromDatasource;
        this.myConfiguration = myConfiguration;
        tables = Lists.newArrayList();
    }

    public void run(String... args) throws Exception {
        if( args != null && args.length != 0){
            for (String arg : args) {
                tables.add(arg);
            }
        }

        // 获取对应的数据表的信息
        List<SingleClass> singleClasses = inspectFieldFromDatasource.queryInspectFields(tables);

        singleClasses.forEach(s -> fromDatasource(s));
    }

    private void fromDatasource(SingleClass singleClass) {
        //build context
        Map<String, Object> context = Maps.newHashMap();
        context.put("singleClass", singleClass);
        context.put("fieldNames",singleClass.queryFieldName());
        context.put("databaseName", myConfiguration.getDatabaseName());

        toClass(context, singleClass);

        toMapper(context, singleClass);

        toDao(context, singleClass);

//        toReadService(context, singleClass);

//        toReadServiceImpl(context, singleClass);

//        generateTestEmptyXmlCondition(context, singleClass);
//
//        List<RandomListObject> randomListObjects = RandomObjectUtil.toRandomListObject(singleClass.getFields(),  5);
//
//        context.put("insertListObject", randomListObjects.get(0));
//        context.put("totalListObject", randomListObjects);
//
//        generateTestExceptXmlCondition(context, singleClass);
//
//        toRandomTestClass(context, singleClass);
    }

    private void toRandomTestClass(Map<String, Object> context, SingleClass singleClass){
        try{
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate emptyXmlTest = pebbler.compile("mapperTestClass.peb");
            emptyXmlTest.evaluate(stringWriter, context);
            File targetTestXmlFile = new File("test/"+singleClass.getClassName()+"MapperTest.java");
            Files.createParentDirs(targetTestXmlFile);
            Files.write(stringWriter.toString(), targetTestXmlFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("generate test except class, cause:{}" + e.toString());
        }
    }

    public List<RandomListObject> toRandomListObjects(List<Field> fields, int size){
        return RandomObjectUtil.toRandomListObject(fields, size);
    }

    public void generateTestExceptXmlCondition(Map<String, Object> context, SingleClass singleClass){
        try {
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate emptyXmlTest = pebbler.compile("testInsertXml.peb");
            emptyXmlTest.evaluate(stringWriter, context);
            File targetTestXmlFile = new File("test/xml/"+singleClass.getClassName()+"MapperTest-testInsert-expected.xml");
            Files.createParentDirs(targetTestXmlFile);
            Files.write(stringWriter.toString(), targetTestXmlFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("generate test except xml fail cause:" + e.toString());
        }
    }

    // 生成对应的测试文件的 Xml empty文件内容
    public void generateTestEmptyXmlCondition(Map<String, Object> context, SingleClass singleClass){
        try {
            StringWriter stringWriter = new StringWriter(8196);
            PebbleTemplate emptyXmlTest = pebbler.compile("testEmptyXml.peb");
            emptyXmlTest.evaluate(stringWriter, context);
            File targetTestXmlFile = new File("test/xml/"+singleClass.getClassName()+"MapperTest-empty.xml");
            Files.createParentDirs(targetTestXmlFile);
            Files.write(stringWriter.toString(), targetTestXmlFile, Charsets.UTF_8);
        }catch (Exception e){
            System.out.println("generate test xml fail cause:{}" + e.toString());
        }
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
