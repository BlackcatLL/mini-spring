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
                //���controllerע����ڣ���������controller��
                parseHandlerfromController(cls);
            }
        }
    }

    private static void parseHandlerfromController(Class<?> cls){
        //�����÷����ȡ��������з���
        Method[] methods = cls.getDeclaredMethods();
        //������Щ�������ҵ���requestMappingע��ķ���
        for(Method method : methods){
            if (!method.isAnnotationPresent(RequestMapping.class)){
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();
            //���α����������ҵ���requestParamע��Ĳ���
            for(Parameter parameter : method.getParameters()){
                if(parameter.isAnnotationPresent(RequestParam.class)){
                    //��ע�����ȡ������������
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            //�Ѳ���������ת��Ϊ�������ʽ
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);
            //����mappingHandler
            MappingHandler mappingHandler = new MappingHandler(uri,method,cls,params);
            //�ѹ���õ�handler�ŵ�handler�������ľ�̬��������
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
