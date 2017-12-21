package com.dunef.repository;

import com.dunef.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:09 2017/12/19
 * @Modified By:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);




}
