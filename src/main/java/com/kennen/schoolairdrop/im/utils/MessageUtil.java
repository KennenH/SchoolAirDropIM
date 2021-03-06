package com.kennen.schoolairdrop.im.utils;

import com.kennen.schoolairdrop.im.bean.ProtocalWithTime;
import com.kennen.schoolairdrop.im.pojo.Offline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kennen
 * @date 2020/12/15 19:57
 */
public class MessageUtil {
    /**
     * 将从redis中取出来的 ProtocalWithTime 集合转换为Offline集合返回
     *
     * @param collection ProtocalWithTime集合
     * @return Offline集合
     */
    public static List<Offline> protocalToOffline(Collection<Object> collection) {
        List<Offline> list = new ArrayList<>();
        for (Object object : collection) {
            if (object instanceof ProtocalWithTime) {
                ProtocalWithTime protocal = (ProtocalWithTime) object;
                list.add(assemblyOffline(protocal));
            }
        }
        return list;
    }

    /**
     * 将{@link ProtocalWithTime}组装为{@link Offline}返回
     */
    public static Offline assemblyOffline(ProtocalWithTime protocal) {
        Offline offline = new Offline();
        offline.setFinger_print(protocal.getFp());
        offline.setMessage(protocal.getDataContent());
        offline.setMessage_type(protocal.getTypeu());
        offline.setReceived(protocal.isReceived());
        offline.setSender_id(protocal.getFrom());
        offline.setReceiver_id(protocal.getTo());
        offline.setSend_time(protocal.getSendTime());
        return offline;
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