package io.yqj.tools.codegen.template;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 这里假定模板都放在classpath下面, 并以路径作为key来编译并缓存模板
 *
 * Author:  <a href="mailto:i@terminus.io">jlchen</a>
 * Date: 2016-02-03
 */
@Slf4j
@Component
public class Pebbler {

    private final PebbleEngine engine = new PebbleEngine.Builder()
            .loader(new ClasspathLoader())
            .extension(new SqlMapperExtension())
            .build();


    /**
     * 编译pebble模板
     *
     * @param file  模板的路径
     * @return   编译好的pebble模板
     * @throws IOException  乱七八糟的异常
     */
    public PebbleTemplate compile(String file) throws Exception {
         return engine.getTemplate(file);
    }
}
