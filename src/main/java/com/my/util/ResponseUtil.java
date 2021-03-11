package com.my.util;

import com.my.enums.ResponseEnum;
import com.my.response.CommonResponse;

/**
 * @author chenming
 * @date 2021/1/29 9:49
 */
public class ResponseUtil {

    public static CommonResponse success(){
        CommonResponse response = new CommonResponse();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        return response;
    }

    public static <T> CommonResponse success(T obj){
        CommonResponse response = new CommonResponse();
        response.setCode(ResponseEnum.SUCCESS.getCode());
        response.setMsg(ResponseEnum.SUCCESS.getMsg());
        response.setData(obj);
        return response;
    }

    public static CommonResponse custom(ResponseEnum rc){
        CommonResponse response = new CommonResponse();
        response.setCode(rc.getCode());
        response.setMsg(rc.getMsg());
        return response;
    }

    public static <T> CommonResponse custom(ResponseEnum rc, T data){
        CommonResponse response = new CommonResponse();
        response.setCode(rc.getCode());
        response.setMsg(rc.getMsg());
        response.setData(data);
        return response;
    }

}
