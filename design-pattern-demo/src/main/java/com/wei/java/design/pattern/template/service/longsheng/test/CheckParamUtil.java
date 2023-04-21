package com.wei.java.design.pattern.template.service.longsheng.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 校验通用类
 *
 * @Author: lianweizi
 * @Date: 2020/8/17 6:02 下午
 */
public class CheckParamUtil {

    private static final String EMOJI_RULE =
            "(?:[\uD83C\uDF00-\uD83D\uDDFF]|[\uD83E\uDD00-\uD83E\uDDFF]|[\uD83D\uDE00-\uD83D\uDE4F]|[\uD83D\uDE80-\uD83D\uDEFF]|[\u2600-\u26FF]\uFE0F?|[\u2700-\u27BF]\uFE0F?|\u24C2\uFE0F?|[\uD83C\uDDE6-\uD83C\uDDFF]{1,2}|[\uD83C\uDD70\uD83C\uDD71\uD83C\uDD7E\uD83C\uDD7F\uD83C\uDD8E\uD83C\uDD91-\uD83C\uDD9A]\uFE0F?|[\u0023\u002A\u0030-\u0039]\uFE0F?\u20E3|[\u2194-\u2199\u21A9-\u21AA]\uFE0F?|[\u2B05-\u2B07\u2B1B\u2B1C\u2B50\u2B55]\uFE0F?|[\u2934\u2935]\uFE0F?|[\u3030\u303D]\uFE0F?|[\u3297\u3299]\uFE0F?|[\uD83C\uDE01\uD83C\uDE02\uD83C\uDE1A\uD83C\uDE2F\uD83C\uDE32-\uD83C\uDE3A\uD83C\uDE50\uD83C\uDE51]\uFE0F?|[\u203C\u2049]\uFE0F?|[\u25AA\u25AB\u25B6\u25C0\u25FB-\u25FE]\uFE0F?|[\u00A9\u00AE]\uFE0F?|[\u2122\u2139]\uFE0F?|\uD83C\uDC04\uFE0F?|\uD83C\uDCCF\uFE0F?|[\u231A\u231B\u2328\u23CF\u23E9-\u23F3\u23F8-\u23FA]\uFE0F?)";

    /**
     * 校验字符串非空
     *
     * @param object
     */
    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new ProdserviceBaseException(ErrorCodeEnum.PARAMS_NULL);
        }
        if (object instanceof String) {
            String str = (String) object;
            if (str.length() == 0) {
                throw new ProdserviceBaseException(ErrorCodeEnum.PARAMS_NULL);
            }
        }
    }

    /**
     * str not blank
     */
    public static void checkNotBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw new ProdserviceBaseException(ErrorCodeEnum.PARAMS_NULL);
        }
    }

    /**
     * 校验正整数
     *
     * @param object
     */
    public static void checkPositiveNumber(Object object) {
        if (object == null) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
        if (object instanceof Long) {
            Long longNumber = (Long) object;
            if (longNumber <= 0) {
                throw new ValidationException(ValidationCode.ILLEGAL);
            }
        } else if (object instanceof Integer) {
            Integer intNumber = (Integer) object;
            if (intNumber <= 0) {
                throw new ValidationException(ValidationCode.ILLEGAL);
            }
        }
    }

    /**
     * 校验时间区间(开始时间必须不晚于结束时间)
     *
     * @param startTime
     * @param endTime
     */
    public static void checkTimeInteval(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
        if (startTime > endTime) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    /**
     * 校验时间区间(开始时间必须不晚于结束时间),同时存在或者同时不存在
     *
     * @param startTime
     * @param endTime
     */
    public static void checkTimeXOR(Long startTime, Long endTime) {
        if (startTime == null ^ endTime == null) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
        if (startTime != null && startTime > endTime) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    /**
     * 校验时间区间(开始时间必须不晚于结束时间)
     *
     * @param startTime
     * @param endTime
     * @param timeInteval 最大时间间隔数，单位为毫秒
     */
    public static void checkTimeInteval(Long startTime, Long endTime, long timeInteval) {
        checkTimeInteval(startTime, endTime);
        if ((endTime - startTime) > timeInteval) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    /**
     * 校验是否为数字(适合可空字段)
     *
     * @param str
     */
    public static void checkInteger(String str) {
        if (str != null && str.length() != 0) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new ValidationException(ValidationCode.ILLEGAL);
            }
        }
    }

    /**
     * 校验集合是否为null或者size = 0
     *
     * @param collection
     */
    public static void checkCollectionNotEmpty(Collection collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    public static JSONObject checkJSON(String json) {
        try {
            return JSON.parseObject(json);
        } catch (Exception e) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    public static void checkJsonKeySameAndValueJsonNotNull(JSONObject json, String... keys) {
        try {
            checkJsonKeySame(json, keys);
            for (String key : keys) {
                JSONObject valueJson = json.getJSONObject(key);
                if (Objects.isNull(valueJson)) {
                    throw new ValidationException(ValidationCode.ILLEGAL);
                }
            }
        } catch (Exception e) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    public static void checkJsonKeySameAndValueNotBlank(JSONObject json, String... keys) {
        checkJsonKeySame(json, keys);
        checkValueNotBlank(json, keys);
    }

    public static void checkValueNotBlank(JSONObject json, String... keys) {
        for (String key : keys) {
            String value = json.getString(key);
            if (StringUtils.isBlank(value)) {
                throw new ValidationException(ValidationCode.IS_BLANK, "不能为空！");
            }
        }
    }

    private static void checkJsonKeySame(JSONObject json, String... keys) {
        List<String> keyList = Arrays.asList(keys);
        if (Objects.isNull(json) || !CollectionUtils.isEqualCollection(keyList, json.keySet())) {
            throw new ValidationException(ValidationCode.ILLEGAL);
        }
    }

    /**
     * number equals ant func
     *
     * @param num
     * @param searchNums
     * @return
     */
    public static boolean numberEqualsAny(Number num, Number... searchNums) {
        if (num != null && ArrayUtils.isNotEmpty(searchNums)) {
            for (Number searchNum : searchNums) {
                if (num.equals(searchNum)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否是正整数
     *
     * @param object
     */
    public static boolean isPositiveNumber(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Long) {
            Long longNumber = (Long) object;
            if (longNumber <= 0) {
                return false;
            }
        } else if (object instanceof Integer) {
            Integer intNumber = (Integer) object;
            if (intNumber <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验不支持Emoji
     */
    public static void checkEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return;
        }
        if (containsEmoji(source)) {
            throw new RuntimeException("ErrorCodeEnum.PARAMS_NOT_SUPPORT_EMOJI");
        }
    }

    private static boolean containsEmoji(String source) {
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b ||
                        hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }
}
