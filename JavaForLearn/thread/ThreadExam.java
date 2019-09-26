package thread;

import java.util.concurrent.*;

public class ThreadExam {

    public static void main(String[] args) throws Exception {

        /** ThreadPoolExecutor类的参数，前5个位必需
        corePoolSize：核心线程池的大小，如果核心线程池有空闲位置，这是新的任务就会被核心线程池新建一个线程执行，执行完毕后不会销毁线程，线程会进入缓存队列等待再次被运行。

        maximunPoolSize：线程池能创建最大的线程数量。如果核心线程池和缓存队列都已经满了，新的任务进来就会创建新的线程来执行。
         但是数量不能超过maximunPoolSize，否侧会采取拒绝接受任务策略，我们下面会具体分析。

        keepAliveTime：非核心线程能够空闲的最长时间，超过时间，线程终止。这个参数默认只有在线程数量超过核心线程池大小时才会起作用。
         只要线程数量不超过核心线程大小，就不会起作用。

         unit：时间单位，和keepAliveTime配合使用。

         workQueue：缓存队列，用来存放等待被执行的任务。

         threadFactory：线程工厂，用来创建线程，一般有三种选择策略。ArrayBlockingQueue;LinkedBlockingQueue;SynchronousQueue;
        handler：拒绝处理策略，线程数量大于最大线程数就会采用拒绝处理策略，
         四种策略为ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
                     ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
                        ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
                        ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,500, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        //利用多线程计算数组1内的每一项的叠加，如3的叠加=1+2+3=6，再把值赋给数组2的对应项
        Integer[] integers1 = {100,20,30,25,15,3};
        Integer[] integers2 = {0,0,0,0,0,0};
        for(int i = 0 ;i<integers1.length;i++) {
            MyThread thread = new MyThread(integers1,integers2);
         //   MyThread thread1 = new MyThread(integers1,integers2);
          //  Thread th1 = new Thread(thread);
           // th1.start();
            threadPoolExecutor.execute(thread);
           // threadPoolExecutor.execute(thread1);
        }
        for (int i = 0 ; i<integers1.length ; i++) {
         System.out.print(integers1[i]+",");
        }

        System.out.println();
        for (int i = 0 ; i<integers1.length ; i++) {
            System.out.print(integers2[i]+",");
        }
        threadPoolExecutor.shutdown();
    }
}

