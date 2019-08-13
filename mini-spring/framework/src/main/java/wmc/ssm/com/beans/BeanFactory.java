package wmc.ssm.com.beans;

import wmc.ssm.com.web.mvc.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {
    //添加一个属性用来储存bean类型到bean实例的映射
    //通过bean类型在实例里取到对应的bean
    private  static Map<Class<?>,Object> classToBean = new ConcurrentHashMap<>();

    //添加一个方法，从映射中获得bean
    public static Object getBean(Class<?> cls){
        return classToBean.get(cls);
    }

    //bean初始化方法
    public static  void initBean(List<Class<?>> classList) throws Exception {
        //创建一个新的容器
        List<Class<?>> toCreate = new ArrayList<>(classList);
        //初始化bean
        while (toCreate.size() != 0){
            int remainSize = toCreate.size();  //定义变量保存当前容器大小+++++
            for(int i=0;i<toCreate.size();i++){
                //如果完成创建,就从容器里删除
                if(finishCreate(toCreate.get(i))){
                    toCreate.remove(i);
                }
            }
            //类定义容器每次遍历完后都要判断大小有无变化，若无变化则是陷入了死循环则抛出异常
            if(toCreate.size() == remainSize){
                throw new Exception("cycle dependency!");
            }
        }
    }

    //先判断类是否需要初始化为Bean，若无需初始化为Bean，则直接返回TURE，并从初始化返回列表中删除

    private static boolean finishCreate (Class<?> cls) throws IllegalAccessException, InstantiationException {
        if(!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)){
            return  true;
        }
        Object bean = cls.newInstance();
        for(Field field : cls.getDeclaredFields()){
            //若属性被autoWired标记则表示它需要使用依赖注入来解决这个依赖
            if(field.isAnnotationPresent(AutoWired.class)){
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType); //通过类型从BeanFactory里获取Bean
                //若依赖的Bean不存在
                if(reliantBean == null){
                    return  false;
                }
                field.setAccessible(true);  //设置可接触性
                field.set(bean,reliantBean);  //通字段的set方法直接设值
            }
        }
        //字段处理完后就可以把bean放到beanFactory中，并返回true
        classToBean.put(cls,bean);
        return true;
    }
}
