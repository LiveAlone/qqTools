package io.yqj.tools.codegen.randomvalue;

/**
 * Created by yaoqijun on 2017/3/18.
 * default random interface
 */
public interface JavaValueRandom<T> {
    T random(T range);
}
