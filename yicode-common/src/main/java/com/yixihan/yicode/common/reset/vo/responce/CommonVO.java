package com.yixihan.yicode.common.reset.vo.responce;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用-vo
 *
 * @author yixihan
 * @date 2022/11/16 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通用-vo")
public class CommonVO<T> {

    @ApiModelProperty(value = "数据")
    private T data;

    @ApiModelProperty(value = "信息")
    private String message;
    
    public CommonVO(T data) {
        this.data = data;
    }
    
    public static <T> CommonVO<T> create (CommonDtoResult<T> dtoResult) {
        return new CommonVO<> (dtoResult.getData (), dtoResult.getMessage ());
    }
}
