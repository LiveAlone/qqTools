package io.yqj.tools.codegen.randomvalue;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class StringRandom implements JavaValueRandom<String>{

    public String random(String range){
        Integer length = Integer.valueOf(range);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++){
            sb.append(CharRandom.random());
        }
        return sb.toString();
    }
}
