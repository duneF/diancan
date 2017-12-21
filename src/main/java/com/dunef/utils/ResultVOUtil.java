package com.dunef.utils;

import com.dunef.vo.ResultVO;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午2:27 2017/12/19
 * @Modified By:
 */
public class ResultVOUtil {

    private static ResultVO<Object> resultVO;

    public static ResultVO success(Object object){
        resultVO = new ResultVO<> ();
        resultVO.setData ( object );
        resultVO.setCode ( 0 );
        resultVO.setMsg ( "成功" );
        return resultVO;
    }

    public static ResultVO sussess(){
        return success ( null );
    }

    public static ResultVO error(Integer code,String msg){
        resultVO.setCode ( code );
        resultVO.setMsg ( msg );
        return resultVO;
    }


}
