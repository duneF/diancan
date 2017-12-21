package com.dunef.controller;

import com.dunef.dataobject.ProductCategory;
import com.dunef.dataobject.ProductInfo;
import com.dunef.service.CategoryService;
import com.dunef.service.ProductService;
import com.dunef.utils.ResultVOUtil;
import com.dunef.vo.ProductInfoVO;
import com.dunef.vo.ProductVO;
import com.dunef.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:55 2017/12/18
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    private ResultVO<Object> resultVO;
    private List<ProductInfo> productInfoList;
    private ArrayList<Integer> categoryTypeList;
    private List<ProductCategory> productCategoryList;
    private ProductVO productVO;
    private ProductInfoVO productInfoVO1;
    private ArrayList<ProductInfoVO> productInfoVOList;
    private ArrayList<ProductVO> productVOList;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有的上架商品
        productInfoList = productService.findUpAll ();

        //2.查询类目（一次性查询）
        //创建一ArrayList用于存放查询出来的类目Type
        categoryTypeList = new ArrayList<> ();
        //遍历所有的product类目
        for (ProductInfo productInfo : productInfoList) {
            //将get到的product类目Type，install到数组中
            categoryTypeList.add ( productInfo.getCategoryType () );
        }
        //通过 所有的product类目type  Get到 ProductCategory类目表 集合
        productCategoryList = categoryService.findByCategoryTypeIn ( categoryTypeList );
        //数据拼装
        productVOList = new ArrayList<> ();
        for (ProductCategory productCategory : productCategoryList) {
            productVO = new ProductVO ();
            productVO.setCategoryType ( productCategory.getCategoryType () );
            productVO.setCategoryName ( productCategory.getCategoryName () );
            productInfoVOList = new ArrayList<> ();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType ().equals ( productCategory.getCategoryType () )){
                productInfoVO1 = new ProductInfoVO ();
                BeanUtils.copyProperties ( productInfo,productInfoVO1 );
                    productInfoVOList.add ( productInfoVO1 );
                }
            }
            productVO.setProductInfoVOList ( productInfoVOList );
            productVOList.add ( productVO );
        }

//        resultVO = new ResultVO<> ();
//        resultVO.setData ( productVOList );
//        resultVO.setCode ( 0 );
//        resultVO.setMsg ( "成功" );
//        return resultVO;
        //将以上注释的方法封装到Utils
            return ResultVOUtil.success ( productVOList );

    }

}
