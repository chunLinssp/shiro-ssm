package org.shiro.maven.common.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常处理器
 */

public class CustomExceptionResolver implements HandlerExceptionResolver {

    //前端控制器DispatcherServlet 在进行HandlerMapping
    //调用HandlerAdapter执行Handler过程中，如果遇到异常就会执行此方法
    //参数中的handler是最终要执行的Handler，它的真实身份是HandlerMethod
    //ex就是接受de异常信息
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        //输出异常信息
        e.printStackTrace();
        //统一异常处理代码
        //针对系统自定义的CustomException异常，就可以直接从异常中获取异常消息，将异常处理在错误页面中展示
        //异常信息
        String message = null;
        CustomException customException = null;
        // 如果ex是系统自定义的异常，取出异常消息
        if(e instanceof CustomException){
            customException = (CustomException)e;
        }else{
            customException = new CustomException("未知错误");
        }
        message = customException.getMessage();
        httpServletRequest.setAttribute("message",message);
        try{
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(httpServletRequest,httpServletResponse);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ModelAndView();
    }
}
