package com.my.enums;

public enum ResponseEnum {
    /**
     * 通用返回码
     */
    SUCCESS("0000","执行成功"),
    REQUEST_PARAM_INVALID("0002","非法请求参数"),
    DATA_NOT_FUND("0003","没有找到返回数据"),

    /**
     * 统一错误返回
     */
    NETWORK_ERROR("9998","网络异常"),
    UNKNOWN_INTERNAL_ERROR("9999","未知内部异常");

    private String code;
    private String msg;

    ResponseEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
