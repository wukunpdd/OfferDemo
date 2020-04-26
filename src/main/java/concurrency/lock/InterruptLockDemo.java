package concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wukun
 * @Date 2020/4/26 10:49
 */
public class InterruptLockDemo {

    public static ReentrantLock lock1 = new ReentrantLock(); //第一把锁 lock1
    public static ReentrantLock lock2 = new ReentrantLock(); //第二把锁 lock2

    public Thread lock1() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    lock1.lockInterruptibly(); //如果当前线程未被中断，则获取锁
                    Thread.sleep(300);
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + ", 执行完毕！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //在业务逻辑执行结束后，检查当前线程是否持有该锁，如果持有则释放该锁
                    if(lock1.isHeldByCurrentThread()){
                        lock1.unlock();
                    }
                    if(lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + ", 退出！");
                }
            }
        });

        thread.start();
        return thread;
    }

    public Thread lock2() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    lock2.lockInterruptibly(); //如果当前线程未被中断，则获取锁
                    Thread.sleep(300);
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + ", 执行完毕！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //在业务逻辑执行结束后，检查当前线程是否持有该锁，如果持有则释放该锁
                    if(lock1.isHeldByCurrentThread()){
                        lock1.unlock();
                    }
                    if(lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + ", 退出！");
                }
            }
        });

        thread.start();
        return thread;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        InterruptLockDemo lockDemo = new InterruptLockDemo();
        Thread thread1 = lockDemo.lock1();
        lockDemo.lock2();

        //自旋一段时间后，如果等待时间过长，则可能发生了死锁问题，主动中断并释放锁
        while(true){
            if(System.currentTimeMillis() - time > 3000){
                thread1.interrupt();
            }
        }
    }
}
