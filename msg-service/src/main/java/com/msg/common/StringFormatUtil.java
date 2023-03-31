package com.msg.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringFormatUtil {
    private static final Pattern pattern = Pattern.compile("\\{(.*?)\\}");
    private static Matcher matcher;

    private StringFormatUtil() {
    }

    /**
     * 格式化字符串 字符串中使用{key}表示占位符
     *
     * @param sourStr 需要匹配的字符串
     * @param param   参数集
     * @return
     */
    public static String format(String sourStr, Map<String, Object> param) {
        String tagerStr = sourStr;
        if (param == null)
            return tagerStr;
        try {
            matcher = pattern.matcher(tagerStr);
            while (matcher.find()) {
                String key = matcher.group();
                String keyclone = key.substring(1, key.length() - 1).trim();
                Object value = param.get(keyclone);
                if (value != null)
                    tagerStr = tagerStr.replace(key, value.toString());
            }
        } catch (Exception e) {
            log.error("format", e);
            return null;
        }
        return tagerStr;
    }
}
