package com.dunef.exception;

import com.dunef.enums.ResultEnum;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:11 2017/12/19
 * @Modified By:
 */
public class SellException extends RuntimeException{

        private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage ());
        this.code=resultEnum.getCode ();
    }
}
