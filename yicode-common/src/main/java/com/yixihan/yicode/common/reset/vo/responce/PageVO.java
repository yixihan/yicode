package com.yixihan.yicode.common.reset.vo.responce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * open 层分页封装类
 * @author yixihan
 * @date 2022-09-29-14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("open 层分页封装类")
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -2763957656843891790L;

    @ApiModelProperty("当前页")
    private Long current;

    @ApiModelProperty("总条数")
    private Long total;

    @ApiModelProperty("每页展示条数")
    private Long size;

    @ApiModelProperty("pages")
    private Long pages;

    @ApiModelProperty("数据")
    private List<T> records;
}
