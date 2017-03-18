package io.yqj.tools.codegen.randomvalue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yaoqijun on 2017/3/18.
 */
public class CharRandom implements JavaValueRandom<Character>{

    public static final Integer CHAR_COUNT = 52;

    public static Character random(){
        int pos = ThreadLocalRandom.current().nextInt(CHAR_COUNT);
        if (pos > 25) {
            return (char)(97 + pos - 26);
        }else {
            return (char)(65 + pos);
        }
    }

    @Override
    public Character random(Character range) {
        return random();
    }
}
