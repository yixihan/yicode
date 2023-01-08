package com.yixihan.yicode.runcode.run.dto.response;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码运行-dtoResult
 *
 * @author yixihan
 * @date 2023/1/8 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("代码运行-dtoResult")
public class CodeRunDtoResult {
    
    @ApiModelProperty(value = "代码运行结果")
    private List<String> ansList;
    
    @ApiModelProperty(value = "是否编译成功")
    private Boolean compile;
    
    @ApiModelProperty(value = "时间消耗")
    private Long useTime;
    
    @ApiModelProperty(value = "内存消耗")
    private Double useMemory;
    
    public static CodeRunDtoResult error (Throwable e) {
        CodeRunDtoResult dtoResult = new CodeRunDtoResult ();
        dtoResult.setAnsList (CollUtil.newArrayList (e.getMessage ()));
        return dtoResult;
    }
}
