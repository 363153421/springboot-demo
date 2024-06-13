package com.wkf.tablesaw.controller;

import com.wkf.tablesaw.utils.ResponseResult;
import com.wkf.tablesaw.utils.ResultVOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuKeFan
 * @date 2024-06-13 09:53:42
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public ResponseResult<Object> showHelloWorld(){
        return ResultVOUtils.success("HelloWorld");
    }

}
