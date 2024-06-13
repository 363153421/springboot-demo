package com.wkf.tablesaw.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuKeFan
 * @date 2024-01-12 10:24:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    public static final ResponseResult<?> EMPTY = new ResponseResult<>();

    private int code; // 结果码，对应结果码表
    private String message; // 结果描述
    private T result; // 结果
}
