package concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wukun
 * @Date 2020/4/26 10:31
 */
public class ReentrantLockDemo implements Runnable {

    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static int i = 1;

    public void run() {
        reentrantLock.lock();
        for(int k = 0; k < 10; k++){
            try{
                i++;
            } finally {
                //finally中前面的代码抛出异常后，后面的代码不会再执行
                reentrantLock.unlock();
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread thread = new Thread(reentrantLockDemo);
        //就绪状态，在获取到CPU资源时才会执行
        thread.start();
        //当前线程转为阻塞状态，等待另一个线程结束
        thread.join();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("会不会中途打断呢？爷想试下");
            }
        }).start();

        System.out.println(i);
    }
}
