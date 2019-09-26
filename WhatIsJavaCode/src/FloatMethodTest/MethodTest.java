package FloatMethodTest;

public class MethodTest {
    float x = 4;

    public void f1(float x){
        x = 55;
    }
    public void f2(MethodTest methodTest){
        methodTest.x = 10;
    }
    public static void main(String[] args) {
        MethodTest methodTest1 = new MethodTest();
        MethodTest methodTest2 = new MethodTest();
        methodTest1.f1(methodTest1.x);
        methodTest2.f2(methodTest2);
        System.out.println(methodTest1.x);
        System.out.println(methodTest2.x);
    }
}
