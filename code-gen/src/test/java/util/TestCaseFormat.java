package util;

import io.yqj.tools.codegen.util.CaseFormatUtil;

/**
 * Created by yaoqijun.
 * Date:2016-05-20
 * Email:yaoqj@terminus.io
 * Descirbe:
 */
public class TestCaseFormat {
    public static void main(String[] args) {
        System.out.println(CaseFormatUtil.lowCamelToLowSub("yaoQijun"));
        System.out.println(CaseFormatUtil.lowSubToLowCamel("yao_qi_jun"));
        System.out.println(CaseFormatUtil.lowSubToUpCamel("yao_qi_jun"));
        System.out.println(CaseFormatUtil.upCamelToLowSub("YaoQiJun"));
    }
}
