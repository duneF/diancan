package com.dunef.enums;

import lombok.Getter;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:08 2017/12/18
 * @Modified By:
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
