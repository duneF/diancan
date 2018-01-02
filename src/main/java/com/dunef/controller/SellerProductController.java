package com.dunef.controller;

import com.dunef.dataobject.ProductCategory;
import com.dunef.dataobject.ProductInfo;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.form.ProductForm;
import com.dunef.service.CategoryService;
import com.dunef.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
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
    @Autowired
    private CategoryService categoryService;

    private PageRequest request;
    private Page<ProductInfo> productInfoPage;
    private ProductInfo productInfo;
    private List<ProductCategory> categoryList;

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

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false)String productId,
                              Map<String,Object>map){
        if (!StringUtils.isEmpty ( productId )) {
            productInfo=productService.findOne ( productId );
            map.put ( "productInfo",productInfo );
        }
        //查询所有的类目
        categoryList = categoryService.findAll ();
        map.put ( "categoryList",categoryList );
        return new ModelAndView ( "product/index",map );
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map){
        if (bindingResult.hasErrors ()){
            map.put ( "msg",bindingResult.getFieldError ().getDefaultMessage () );
            map.put ( "url","/dianCan/seller/product/index" );
            return new ModelAndView ( "common/error",map );
        }
        try {
            productInfo=productService.findOne ( form.getProductId () );
            BeanUtils.copyProperties (form,productInfo);
            productService.save ( productInfo );
        } catch (SellException e) {
            map.put ( "msg",e.getMessage () );
            map.put ( "url","/dianCan/seller/product/index" );
            return new ModelAndView ( "common/error",map );
        }
        map.put ( "url","/dianCan/seller/product/list" );
        return new ModelAndView ( "common/success",map );
    }



}
