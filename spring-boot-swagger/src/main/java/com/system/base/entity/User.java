package com.system.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author wushengbin
 * @Date 2021/3/4 19:15
 **/
@Data
@ApiModel("用户信息实体类")
public class User {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "页码")
    private String pageno;

    @ApiModelProperty(value = "数量")
    private String pagesize;

}
