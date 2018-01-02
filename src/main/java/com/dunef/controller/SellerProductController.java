package com.dunef.controller;

import com.dunef.dataobject.ProductInfo;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午2:35 2018/1/2
 * @Modified By:
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    private PageRequest request;
    private Page<ProductInfo> productInfoPage;
    private ProductInfo productInfo;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1" )Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String ,Object> map){
        request = new PageRequest ( page - 1, size );
        productInfoPage = productService.findAll ( request );
        map.put ( "productInfoPage",productInfoPage );
        map.put ( "currentPage",page);
        map.put ( "size",size );
        return new ModelAndView ( "product/list",map );
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam(value = "productId")String productId,
                               Map<String ,Object>map){
        try {
            productInfo = productService.onSale ( "124" );
        } catch (SellException e) {
            map.put ( "msg",e.getMessage () );
            map.put ( "url","/dianCan/seller/product/list" );
            return new ModelAndView ( "common/error",map );
        }
      //  map.put ( "msg",ResultEnum.SUCCESS );
        map.put ( "url","/dianCan/seller/product/list" );
        return new ModelAndView ( "common/success",map );
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam(value = "productId")String productId,
                                Map<String,Object>map){
        try {
            productInfo=productService.offSale ( productId );
        } catch (SellException e) {
            map.put ( "msg",e.getMessage () );
            map.put ( "url","/dianCan/seller/product/list" );
            return new ModelAndView ( "common/error",map );
        }
        map.put ( "url","/dianCan/seller/product/list" );
      //  map.put ( "url","/list" );
        return new ModelAndView ( "common/success",map );
    }

}
