package com.wei.java.utils.chinese.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.velocity.util.StringUtils;

import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

/**
 * 该方法主要实现将汉字转换成对应的拼音。
 */
public class ChineseToPinYin {
    private static String path = "/pinyin.properties";

    public ChineseToPinYin() {
    }

    /**
     * @param chinese 需要转换的汉字
     * @return 拼音，默认首字母大写，转换失败结果返回原值
     */
    public String evaluate(String chinese) {
        if (chinese == null) {
            return null;
        }
        String result;
        String pinyin = convertToPinyin(chinese);
        if (pinyin != null && pinyin.length() > 0) {
            result = StringUtils.capitalizeFirstLetter(pinyin);
        } else {
            result = pinyin;
        }
        return result;
    }

    /**
     * @param chinese   需要转换的汉字
     * @param firstIsUp 结果首字母是否大写，默认是
     * @return 拼音
     */
    public String evaluate(String chinese, Boolean firstIsUp) {
        if (chinese == null) {
            return null;
        }
        String result = null;
        if (firstIsUp) {
            String pinyin = convertToPinyin(chinese.toString());
            if (pinyin != null && pinyin.length() > 0) {
                result =
                        StringUtils.capitalizeFirstLetter(pinyin);
            } else {
                result = pinyin;
            }
        } else {
            result =
                    convertToPinyin(chinese.toString());
        }
        return result;
    }

    /**
     * @param chinese      需要转换的汉字
     * @param firstIsUp    结果首字母是否大写，默认是
     * @param resultIsNull 转换失败结果是否返回空，默认否
     * @return 拼音
     */
    public String evaluate(String chinese, Boolean firstIsUp, Boolean resultIsNull) {
        if (chinese == null) {
            return null;
        }
        String lowerResult = convertToPinyin(chinese.toString());
        String result = null;
        if (resultIsNull && lowerResult.matches("[\\u4E00-\\u9FA5]+")) {
            return null;
        } else {
            String upperResult = StringUtils.capitalizeFirstLetter(lowerResult);
            result = firstIsUp ? upperResult : lowerResult;
        }
        return result;
    }

    public String convertToPinyin(String chinese) {
        String chinese2 = diffPro(chinese);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] chars = chinese2.toCharArray();
        String result = "";
        try {
            for (int i = 0; i < chinese2.length(); i++) {
                if (java.lang.Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] strarr = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                    result += strarr[0];
                } else {
                    result += java.lang.Character.toString(chars[i]);
                }
            }
            return result;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return chinese;
//        try {
//            result = PinyinHelper.toHanyuPinyinString(chinese, pyFormat, "");
//        } catch (BadHanyuPinyinOutputFormatCombination e) {
//            return null;
//        }
    }

    /**
     * 替换多音字
     *
     * @param chinese 汉字
     * @return 把多音字替换成拼音后的结果
     */
    private static String diffPro(String chinese) {
        Properties properties = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(ChineseToPinYin.class.getResourceAsStream(path), "utf-8");
            properties.load(reader);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {
            String hanzi = key.toString();
            String pinyin = properties.getProperty(hanzi);
            // 判断chinese里是否含有多音字，如果有就把多音字替换成拼音
            if (chinese.contains(hanzi)) {
                chinese = chinese.replaceAll(hanzi, pinyin);
            }
        }
        return chinese;
    }

    //main方法里面测试，将汉语转换成拼音输出
    public static void main(String[] args) throws Exception {
        ChineseToPinYin udfpy = new ChineseToPinYin();
//        FileReader fr = new FileReader("/Users/fengpengwei/Desktop/China_all_city.txt");
//        BufferedReader bf = new BufferedReader(fr);
//        String valueString = null;
//        while ((valueString=bf.readLine())!=null){
//            System.out.println(udfpy.evaluate(new Text(valueString)));
//            System.out.println(valueString);
//        }

        String testStr = null;
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr));

        testStr = new String("");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, true));

        testStr = new String("   \t");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, true));

        testStr = new String("测试哈哈");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, true));

        testStr = new String("测试Rosie11156-test");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, true));

        testStr = new String("上海aaa sdf ?:" +
                "sdf " +
                "sdf sa ");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, true));

        testStr = new String("栃木県");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr, false, false));

        testStr = new String("单雄信");
        System.out.println("===========<" + testStr + ">");
        System.out.println(udfpy.evaluate(testStr));
    }
}

