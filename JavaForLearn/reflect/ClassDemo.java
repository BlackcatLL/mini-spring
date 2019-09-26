package reflect;

public class ClassDemo {
    public static void main(String[] args) {
        Class c1=int.class;
        Class c2=String.class;
        Class c3=double.class;
        Class c4=float.class;
        Class c5=void.class;

        System.out.println(c1.getName());
        System.out.println(c2.getName()+"       "+"axe");
        System.out.println(c2.getSimpleName());  //不包含包名的类的名称
        System.out.println();
    }
}
