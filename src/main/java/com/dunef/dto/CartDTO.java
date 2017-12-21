package com.dunef.dto;

import lombok.Data;

/**
 * @Author:dunef
 * @Description:购物车
 * @Date:Created in 下午8:43 2017/12/19
 * @Modified By:
 */
@Data
public class CartDTO {
    //商品ID
    private String productId;
    //数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
