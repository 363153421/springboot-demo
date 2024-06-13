package com.wkf.tablesaw.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuKeFan
 * @date 2024-06-13 10:09:37
 */
@Slf4j
public class ResultVOUtils {
    public static <T> ResponseResult<T> success(T object) {
        ResponseResult<T> resultVO = new ResponseResult<T>();
        resultVO.setCode(0);
        resultVO.setMessage("success");
        resultVO.setResult(object);
        if (log.isDebugEnabled()) {
            log.debug("generate rest result:{}", resultVO);
        }
        return resultVO;
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> error(String msg) {
        ResponseResult<T> resultVO = new ResponseResult<>();
        resultVO.setCode(500);
        resultVO.setMessage(msg);
        return resultVO;
    }

    public static <T> ResponseResult<T> error(int code, String msg) {
        ResponseResult<T> resultVO = new ResponseResult<>();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        return resultVO;
    }
}

