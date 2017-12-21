package com.dunef.repository;

import com.dunef.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:29 2017/12/18
 * @Modified By:
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
 List<ProductInfo> findByProductStatus(Integer productStatus);

}
