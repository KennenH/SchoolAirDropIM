package com.kennen.schoolairdrop.im.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/28 22:29
 */
public class MyUtil {

    public static List<String> getArrayFromString(String picSet) {
        return Arrays.asList(picSet.split("&"));
    }
}
