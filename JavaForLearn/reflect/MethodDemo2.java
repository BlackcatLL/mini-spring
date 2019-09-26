package reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodDemo2 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        ArrayList<String> list1 = new ArrayList<String>();
        System.out.println(list.equals(list1));
        list1.add("hello");
        Class c1=list.getClass();
        Class c2=list1.getClass();
        System.out.println(c1==c2);
        //反射的操作都是编译之后的操作
        /**
         * c1==c2结果返回true说明编译之后集合的泛型是去泛型化的
         * java中集合的泛型，是防止错误输入的，只在编译阶段有效，
         * 绕过编译就无效了。
         * 验证：通过方法的反射来绕过编译
         */

        try{
            Method m=c2.getMethod("add",Object.class);
            m.invoke(list1,100);
            System.out.println(list1);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
