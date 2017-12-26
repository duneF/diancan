package com.dunef.enums;

import lombok.Getter;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午3:52 2017/12/19
 * @Modified By:
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
