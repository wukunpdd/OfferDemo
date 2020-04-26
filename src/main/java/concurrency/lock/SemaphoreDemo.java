package concurrency.lock;

import java.util.concurrent.Semaphore;

/**
 * @Author wukun
 * @Date 2020/4/26 11:09
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //创建一个计数阈值为5的信号量对象，即只能有五个线程同时访问
        Semaphore semaphore = new Semaphore(5);
        try {
            semaphore.acquire(); //申请许可
            try {
                //执行业务逻辑
            } catch (Exception e) {

            } finally {
                //释放许可
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
