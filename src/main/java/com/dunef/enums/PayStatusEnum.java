package com.dunef.enums;

import lombok.Getter;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:00 2017/12/19
 * @Modified By:
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;


    private Integer code;
    private  String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
