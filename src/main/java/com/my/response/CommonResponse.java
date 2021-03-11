package com.my.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CommonResponse<T> implements Serializable {
    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("状态信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;
    @ApiModelProperty("扩展信息")
    private String extend;

    public void appendExtend(String append){
        if(this.extend == null){
            this.extend = append;
        }else{
            this.extend = this.extend + " || " + append;
        }
    }

}
