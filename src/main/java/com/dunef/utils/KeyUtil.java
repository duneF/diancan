package com.dunef.utils;

import java.util.Random;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:47 2017/12/19
 * @Modified By:
 */
public class KeyUtil {

    private static Random random;

    /*
            * 生成唯一的主键
            * 格式：时间+随机数
            * @return
            *
            * */
    public static synchronized String genUniqueKey(){
        random = new Random ();
        System.currentTimeMillis ();
        Integer number= random.nextInt (900000)+100000;
        return System.currentTimeMillis ()+String.valueOf ( number );
    }
}
