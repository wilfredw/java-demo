package com.wei.java.utils.sql;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mybatis xml文件中在 order by sql 中对$符号参数进行校验
 * 如下：
 * order by ${@com.tuya.bizplatform.util.sql.SqlUtil@escapeOrderBy(orderBy)}
 *
 * @author buhuan.wang
 * @since 2023/3/16
 */

public final class SqlUtil {
    private static final Pattern ORDER_BY_PAT = Pattern.compile("(`?[a-zA-Z0-9_]+`?(\\s+(desc|asc))?(,\\s*)?)+");
    private static final Pattern GROUP_BY_PAT = Pattern.compile("(`?[a-zA-Z0-9_]+`?)(\\s*,\\s*`?[a-zA-Z0-9_]+`?)*");

    public static String escapeOrderBy(String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            return orderBy;
        } else {
            orderBy = orderBy.trim();
            Matcher mat = ORDER_BY_PAT.matcher(orderBy.toLowerCase());
            return mat.matches() ? orderBy : null;
        }
    }

    public static String escapeGroupBy(String groupBy) {
        if (StringUtils.isBlank(groupBy)) {
            return groupBy;
        } else {
            groupBy = groupBy.trim();
            Matcher mat = GROUP_BY_PAT.matcher(groupBy.toLowerCase());
            return mat.matches() ? groupBy : null;
        }
    }

    private SqlUtil() {
    }
}
