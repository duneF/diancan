package com.dunef.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午10:27 2018/1/2
 * @Modified By:
 */
@Data
public class ProductForm {
    private String productId;
    //名字
    private String productName;
    //单件
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //类目编号
    private Integer categoryType;


}
