package com.dunef.service;

import com.dunef.dataobject.ProductInfo;
import com.dunef.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:56 2017/12/18
 * @Modified By:
 */
public interface ProductService {

    ProductInfo findOne(String productId);
    //查询所有在家商品列表
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
    //上架
    ProductInfo onSale(String productId);
    //下架
    ProductInfo offSale(String productId);

}
