package com.dunef.service.impl;

import com.dunef.dataobject.ProductInfo;
import com.dunef.dto.CartDTO;
import com.dunef.enums.ProductStatusEnum;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.repository.ProductInfoRepository;
import com.dunef.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:54 2017/12/18
 * @Modified By:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    private ProductInfo productInfo;
    private int result;


    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne ( productId );
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus ( ProductStatusEnum.UP.getCode ());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save ( productInfo );
    }


    //增加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            productInfo= repository.findOne ( cartDTO.getProductId () );
            if (productInfo==null){
                throw new SellException ( ResultEnum.PRODUCT_NOT_EXIST );
            }
            result = productInfo.getProductStock () + cartDTO.getProductQuantity ();
            productInfo.setProductStock ( result );
            repository.save ( productInfo );

        }
    }
    //减少库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            productInfo = repository.findOne ( cartDTO.getProductId () );
            if (productInfo ==null){
                throw new SellException ( ResultEnum.PRODUCT_NOT_EXIST );
            }
            result = productInfo.getProductStock () - cartDTO.getProductQuantity ();
            if (result<0){
                throw new SellException ( ResultEnum.PRODUCT_STOCK_ERROR );
            }
            productInfo.setProductStock ( result );
            repository.save ( productInfo );
        }
    }
}
