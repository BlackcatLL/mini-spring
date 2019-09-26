package thread;

public class MyThead01 implements Runnable{
    int num ;
    Object obj = new Object();
    public MyThead01(int num ){
        this.num = num ;
    }
    @Override
    public void run() {
        Thread thread = new Thread();
        synchronized(obj) {
            for (int i=1 ; i <= 100; i++) {
                System.out.println(thread.currentThread().getName() + ":" + i + "-" + num);
            }
        }
    }
}
