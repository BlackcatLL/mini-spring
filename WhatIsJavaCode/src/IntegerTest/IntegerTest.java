package IntegerTest;

public class IntegerTest {


    public static void main(String[] args) {
        //"10",new Integer()
       Integer i = new Integer(10);
       // int j = 10;
       Integer j = 10;
       //int k = 10;
       int k = 10;
        System.out.println(i==j);//false 用new创建的对象不会放到缓存中，直接Integer赋值的会放到缓存中，地址不一样，即不是同一对象
        System.out.println(i==k);//true  Integer的自动拆箱功能，比较的是基本数据类型的值，所以相等
        System.out.println(j==k);//true
        System.out.println(i.equals(j));//true
        System.out.println(j.equals(k));//true
    }
}
