package reflect;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Office{
    public static  void main(String[] args)
    {
        try {
            System.out.print("请输入:");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String str=br.readLine();
            //动态加载类，运行时加载
            Class c = Class.forName(str);
            //通过类类型，创建该类对象
            OfficeAble oa=(OfficeAble)c.newInstance();
            oa .start();
        }catch ( Exception ClassNotFoundException)
        {
            System.out.println("未找到该应用！");
        }
    }
}
