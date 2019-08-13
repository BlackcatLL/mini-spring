package wmc.ssm.com.web.handler;

import wmc.ssm.com.beans.BeanFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MappingHandler {
    //每个MappingHandle都是一个请求映射器
    private String uri;
    private Method method;
    private Class<?> controller;
    private String[] args;

    public boolean handle(ServletRequest req , ServletResponse res) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if(!uri.equals(requestUri)){
            return false;
        }

        Object[] paramters = new Object[args.length];
        //根据参数名依次从ServletRequest里面获取参数
        for(int i=0;i<args.length;i++){
            paramters[i] = req.getParameter(args[i]);
        }
        //实例化controller
        Object ctl = BeanFactory.getBean(controller);
        Object response = method.invoke(ctl,paramters);
        //把方法返回的结果放到ServletResponse里去
        res.getWriter().println(response.toString());
        return true;
    }
    MappingHandler(String uri,Method method,Class<?> cls,String[] args){
        this.args = args;
        this.controller = cls;
        this.uri = uri;
        this.method = method;
    }
}
