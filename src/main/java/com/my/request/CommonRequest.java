package com.my.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonRequest {
    @ApiModelProperty("第几页")
    private Integer pageNum;
    @ApiModelProperty("每页条数")
    private Integer pageSize;
    @ApiModelProperty("总页数")
    private Integer pageCount;
}
