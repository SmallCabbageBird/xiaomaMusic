package com.xiaoma.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {

    private UuidUtil(){}

    public static String getUuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
    }
    /**
     * 获得订单编号
     * @return
     */
    public static int getOrderOID() {
        SimpleDateFormat format = new SimpleDateFormat("HHmmssSSS");
        String strDate = format.format(new Date());
        return Integer.parseInt(strDate);
    }
}
