package com.example.springboot.swagger.demo.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leejiawei
 */
@Data
@Api(tags = "用户")
public class User {
    @ApiModelProperty(value = "主键id ${User.id}", required = true,example = "1")
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名 ${User.name}", required = true)
    private String name;

}
