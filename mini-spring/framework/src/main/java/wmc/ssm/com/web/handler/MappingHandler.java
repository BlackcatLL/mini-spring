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
    //ÿ��MappingHandle����һ������ӳ����
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
        //���ݲ��������δ�ServletRequest�����ȡ����
        for(int i=0;i<args.length;i++){
            paramters[i] = req.getParameter(args[i]);
        }
        //ʵ����controller
        Object ctl = BeanFactory.getBean(controller);
        Object response = method.invoke(ctl,paramters);
        //�ѷ������صĽ���ŵ�ServletResponse��ȥ
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
