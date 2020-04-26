package concurrency.lock;

import java.util.concurrent.Semaphore;

/**
 * Semaphore：信号量，用于控制同时访问某些资源的线程个数
 * eg：公司有五个员工，只有两台打印机，同时打印资料的员工数只能是2个，其余三天要等待2人中有人打印完成
 *
 * @Author wukun
 * @Date 2020/4/26 11:09
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        int number = 5; //设置线程数
        Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i < number; i++){
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int number;

        private Semaphore semaphore;

        public Worker(int number, Semaphore semaphore) {
            this.number = number;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(); //线程申请资源
                System.out.println("员工" + this.number + "占用一个打印机...");
                Thread.sleep(2000);
                System.out.println("员工" + this.number + "打印完成释放资源");
                semaphore.release(); //线程释放资源
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
