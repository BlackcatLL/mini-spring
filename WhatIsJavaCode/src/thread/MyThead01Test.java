package thread;

public class MyThead01Test {
    public static void main(String[] args) {
        MyThead01 thead01 = new MyThead01(100);
        Thread thread_01 = new Thread(thead01);
        thread_01.setName("张三");
        thread_01.start();

        MyThead01 thead02 = new MyThead01(200);
        Thread thread_02 = new Thread(thead02);
        thread_02.setName("李四");
        thread_02.start();
    }


}
