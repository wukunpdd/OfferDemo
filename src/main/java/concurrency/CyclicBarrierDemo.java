package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：是一个同步工具，可以实现让一组线程等待至某个状态之后再全部同时执行
 *
 * @Author wukun
 * @Date 2020/4/26 15:30
 */
public class CyclicBarrierDemo {

    public static int number = 1;

    public static void main(String[] args) {
        int N = 4;
        //定义CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for(int i = 0; i < N; i++) {
            new BusinessThread(cyclicBarrier).start();
        }
    }

    static class BusinessThread extends Thread {
        private CyclicBarrier cyclicBarrier;

        public BusinessThread(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //执行线程业务逻辑
                Thread.sleep(number * 1000);
                number++;

                System.out.println(number + "线程执行前准备工作完成，等待其它线程准备工作完成");

                //业务线程执行完成，等待其它线程也成为Barrier状态
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            //所有线程准备工作均完成，开始执行下一项任务
            System.out.println("所有线程准备工作均完成，开始执行下一项任务");
            //这里写需要并发执行的下一阶段的工作代码
        }
    }
}
