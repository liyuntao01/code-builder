package com.hope.core.util;


/**
 * @author haha
 */
public class StringUtils {

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }


    public static String substringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != -1) {
            final int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            } else {
                return str.substring(start + open.length());
            }
        } else {
            final int end = str.indexOf(close, open.length());
            if (end != -1) {
                return str.substring(open.length(), end);
            } else {
                return str.substring(open.length());
            }
        }
    }

    /**
     * 计算字符传出现次数
     * @param text
     * @param searchText
     * @return
     */
    public static int getStrCount(String text, String searchText) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(searchText)) {
            return 0;
        }
        int originLength = text.length();
        int newLength = text.replaceAll(searchText, "").length();
        int count = (originLength - newLength) / searchText.length();
        return count;
    }

}
