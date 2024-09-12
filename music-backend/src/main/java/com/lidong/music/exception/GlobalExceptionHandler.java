package com.lidong.music.exception;


import com.lidong.music.entity.ResponseVO;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    //配置抛出异常时把异常信息也返回到浏览器
    @ExceptionHandler(BusinessException.class)
    public ResponseVO handleBusinessException(BusinessException ex) {
        ResponseVO responseVO = new ResponseVO();
        // 从 BusinessException 中获取异常信息
        String errorMessage = ex.getMessage();
        // 这里可以根据业务需要自定义返回的错误信息或状态码
        Integer code = ex.getCode();
        System.out.println("code是：" + ex.getCodeEnum());

        // 可以根据实际需要进一步处理异常信息

        // 返回适当的响应
        responseVO.setCode(code);
        responseVO.setInfo(errorMessage);
        return responseVO;
    }
}

