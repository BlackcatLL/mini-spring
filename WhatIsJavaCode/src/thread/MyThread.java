package thread;

import java.util.concurrent.Callable;

public class MyThread implements Runnable {

    private volatile Integer[] x1;
    private volatile Integer[] x2;
    Object obj = new Object();
    MyThread(Integer[] x1,Integer[] x2){
        this.x1 = x1 ;
        this.x2 = x2 ;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < x1.length; i++) {
                int sum = 0;
      //          System.out.println(Thread.currentThread().getName() +"计算前");
                for (int j = 1; j <= x1[i]; j++) {
                    sum = sum + j;
                }
                x2[i] = sum;
         //      System.out.println("线程" + Thread.currentThread().getName() + "计算得" + sum);
            }

        }
    }
}
