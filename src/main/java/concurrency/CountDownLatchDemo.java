package concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：是一个同步工具类，允许一个线程或多个线程一直等待其它线程的操作执行完后再执行其它相关操作
 * 基于线程计数器来实现并发访问控制，主要用于主线程等待其它主线程都执行完毕后执行相关操作
 *
 * @Author wukun
 * @Date 2020/4/26 15:10
 */
public class CountDownLatchDemo {

    final static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("等待线程1 start");
                    Thread.sleep(3000);
                    System.out.println("等待线程1 end");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("等待线程2 start");
                    Thread.sleep(5000);
                    System.out.println("等待线程2 end");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("开始等待...");
                    latch.await();
                    System.out.println("子线程终于执行完了，老子要开动了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
