package com.dunef.repository;

import com.dunef.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:13 2017/12/19
 * @Modified By:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderId(String orderId);


}
