package io.yqj.tools.codegen.component;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import io.yqj.tools.codegen.model.SingleClass;
import io.yqj.tools.codegen.util.ClassFieldAnalyse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yaoqijun.
 * Date:2016-04-23
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
@Component
public class InspectFieldFromBeans {

    private static final String beanPath = "io.terminus.tools.codegen.beans";

    /**
     * get all bean from class load
     * @return
     */
    public List<SingleClass> queryAllClassInBeans() throws Exception{

        List<SingleClass> singleClasses = Lists.newArrayList();

        ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        for (ClassPath.ClassInfo info :classPath.getTopLevelClasses(beanPath)){
            singleClasses.add(SingleClass.buildByClassName(info.getSimpleName(),"todo", ClassFieldAnalyse.classFields(info.load())));
        }
        return singleClasses;
    }
}
