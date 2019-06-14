package wmc.ssm.com.web.handler;

import wmc.ssm.com.web.mvc.Controller;
import wmc.ssm.com.web.mvc.RequestMapping;
import wmc.ssm.com.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class HandlerManager {
    public  static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static  void resolveMappingHandler(List<Class<?>> classList){
        for(Class<?> cls : classList){
            if(cls.isAnnotationPresent(Controller.class)){
                //如果controller注解存在，则解析这个controller类
                parseHandlerfromController(cls);
            }
        }
    }

    private static void parseHandlerfromController(Class<?> cls){
        //先利用反射获取到类的所有方法
        Method[] methods = cls.getDeclaredMethods();
        //遍历这些方法，找到被requestMapping注解的方法
        for(Method method : methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();
            //依次遍历参数，找到被requestParam注解的参数
            for(Parameter parameter : method.getParameters()){
                if(parameter.isAnnotationPresent(RequestParam.class)){
                    //从注解里获取到参数的名字
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            //把参数名容器转化为数组的形式
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);
            //构造mappingHandler
            MappingHandler mappingHandler = new MappingHandler(uri,method,cls,params);
            //把构造好的handler放到handler管理器的静态属性里面
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
