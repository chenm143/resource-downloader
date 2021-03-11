package com.my.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonControllerAdvice {

    /**
     * 统一异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.addObject("stackTrace",ex.getStackTrace());
        mv.setViewName("error");
        return mv;
    }

    //还是做统一数据 初始化操作
    //统一数据预处理等


}
