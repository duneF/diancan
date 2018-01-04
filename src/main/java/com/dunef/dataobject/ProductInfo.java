package com.dunef.dataobject;

import com.dunef.enums.ProductStatusEnum;
import com.dunef.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午8:34 2017/12/18
 * @Modified By:
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "product_info")
public class ProductInfo {
    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //状态0正常1上架 默认是在架状态
    private Integer productStatus =ProductStatusEnum.UP.getCode ();

    //类目编号
    private Integer categoryType;

    private Date  createTime;
    private Date  updateTime;
    //TODO 为什么pojo impliments CodeEnum,反射就不报错
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode (productStatus,ProductStatusEnum.class);
    }



//    public ProductInfo() {
//    }
//
//    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productStock = productStock;
//        this.productDescription = productDescription;
//        this.productIcon = productIcon;
//        this.productStatus = productStatus;
//        this.categoryType = categoryType;
//    }
}
