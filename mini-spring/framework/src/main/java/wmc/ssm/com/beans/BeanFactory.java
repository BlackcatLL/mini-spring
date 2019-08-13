package wmc.ssm.com.beans;

import wmc.ssm.com.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    //���һ��������������bean���͵�beanʵ����ӳ��
    //ͨ��bean������ʵ����ȡ����Ӧ��bean
    private  static Map<Class<?>,Object> classToBean = new ConcurrentHashMap<>();

    //���һ����������ӳ���л��bean
    public static Object getBean(Class<?> cls){
        return classToBean.get(cls);
    }

    //bean��ʼ������
    public static  void initBean(List<Class<?>> classList) throws Exception {
        //����һ���µ�����
        List<Class<?>> toCreate = new ArrayList<>(classList);
        //��ʼ��bean
        while (toCreate.size() != 0){
            int remainSize = toCreate.size();  //����������浱ǰ������С+++++
            for(int i=0;i<toCreate.size();i++){
                //�����ɴ���,�ʹ�������ɾ��
                if(finishCreate(toCreate.get(i))){
                    toCreate.remove(i);
                }
            }
            //�ඨ������ÿ�α������Ҫ�жϴ�С���ޱ仯�����ޱ仯������������ѭ�����׳��쳣
            if(toCreate.size() == remainSize){
                throw new Exception("cycle dependency!");
            }
        }
    }

    //���ж����Ƿ���Ҫ��ʼ��ΪBean���������ʼ��ΪBean����ֱ�ӷ���TURE�����ӳ�ʼ�������б���ɾ��

    private static boolean finishCreate (Class<?> cls) throws IllegalAccessException, InstantiationException {
        if(!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)){
            return  true;
        }
        Object bean = cls.newInstance();
        for(Field field : cls.getDeclaredFields()){
            //�����Ա�autoWired������ʾ����Ҫʹ������ע��������������
            if(field.isAnnotationPresent(AutoWired.class)){
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType); //ͨ�����ʹ�BeanFactory���ȡBean
                //��������Bean������
                if(reliantBean == null){
                    return  false;
                }
                field.setAccessible(true);  //���ÿɽӴ���
                field.set(bean,reliantBean);  //ͨ�ֶε�set����ֱ����ֵ
            }
        }
        //�ֶδ������Ϳ��԰�bean�ŵ�beanFactory�У�������true
        classToBean.put(cls,bean);
        return true;
    }
}
