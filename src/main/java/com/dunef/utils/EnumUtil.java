package com.dunef.utils;

import com.dunef.enums.CodeEnum;

/**
 * @Author :dunef
 * @Description:
 * @Date:Created in 下午2:16 2017/12/26
 * @Modified By:
 */
public class EnumUtil {
    //TODO 不理解这段，需要再研究,OrderDTO用到此工具类
    public  static <T extends CodeEnum>T getByCode(Integer code, Class<T> enumClass){
            for (T each:enumClass.getEnumConstants ()){
                if (code.equals ( each.getCode () )){
                    return each;
                }
            }

        return null;
    }
}
