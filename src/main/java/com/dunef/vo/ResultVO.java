package com.dunef.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午11:04 2017/12/18
 * @Modified By:
 */
@Data
public class ResultVO <T>{
    private Integer code;

    private String msg;

    private T data;
}
