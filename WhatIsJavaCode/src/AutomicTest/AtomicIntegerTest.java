package AutomicTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子计数器Integer测试
 * public final int getAndSet(int newValue)       给AtomicInteger设置newValue并返回加oldValue
 * public final boolean compareAndSet(int expect, int update)    如果输入的值和期望值相等就set并返回true/false
 * public final int getAndIncrement()     对AtomicInteger原子的加1并返回当前自增前的value
 * public final int getAndDecrement()   对AtomicInteger原子的减1并返回自减之前的的value
 * public final int getAndAdd(int delta)   对AtomicInteger原子的加上delta值并返加之前的value
 * public final int incrementAndGet()   对AtomicInteger原子的加1并返回加1后的值
 * public final int decrementAndGet()    对AtomicInteger原子的减1并返回减1后的值
 * public final int addAndGet(int delta)   给AtomicInteger原子的加上指定的delta值并返回加后的值
 */
public class AtomicIntegerTest {
    private AtomicInteger atomicCount = new AtomicInteger(0);
    private int normalCount = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        atomicIntegerTest.normalInt();
        atomicIntegerTest.AtomicInt();
    }

    public void normalInt() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        normalCount++;
                    }
                }
            }.start();
        }
        Thread.sleep(1000);
        System.out.println("count = " + normalCount);
    }

    public void AtomicInt() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        atomicCount.getAndIncrement();
                    }
                }
            }.start();
        }
        Thread.sleep(1000);
        System.out.println("count = " + atomicCount);

    }

}
