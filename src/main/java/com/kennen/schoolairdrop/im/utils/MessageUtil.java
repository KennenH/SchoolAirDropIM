package com.kennen.schoolairdrop.im.utils;

import java.util.List;

/**
 * @author kennen
 * @date 2020/12/15 19:57
 */
public class MessageUtil {

    /**
     * 将字符串剪切为length长度，不足则取完整字符串
     *
     * @param data   要剪切的数据字符串
     * @param length 要剪切的长度
     */
    public static String cutStringLength(String data, int length) {
        if (length == 0) return "";
        if (data.length() >= length) {
            return data.substring(0, length - 1).concat("…");
        } else {
            return data;
        }
    }

    /**
     * 将指纹数组转换为以单引号引用，逗号分隔的字符串
     *
     * @return 形如 'x','x','x' 一个x代表一个指纹
     */
    public static String listToStringSplitWithDot(List<String> fingerPrints) {
        // 先将头尾中括号替换为单引号
        String replaceStartAndEnd = fingerPrints.toString().replaceAll("[\\[]*[\\]]*", "'");
        // 然后替换各个字串之间的逗号和空格，直接返回
        return replaceStartAndEnd.replaceAll("[,][ ]", "','");
    }
}