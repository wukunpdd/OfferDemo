package concurrency.lock;

/**
 * synchronized 作用于成员变量和非静态方法时，锁住的是对象实例，即this对象
 * synchronized 作用静态方法时，锁住的是Class实例，因为静态方法属于Class而不属于对象
 * synchronized 作用于一个代码块时，锁住的是所有代码块中配置的实例
 *
 * 此类测试的是：synchronized修饰普通的同步方法，锁住的是当前的实例对象
 * @Author wukun
 * @Date 2020/4/26 10:01
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        final SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        new Thread(new Runnable(){

            public void run() {
                synchronizedDemo.generalMethod1();
            }
        }).start();

        new Thread(new Runnable(){

            public void run() {
                synchronizedDemo.generalMethod2();
            }
        }).start();

        /**
         * 输出结果为：
         *  generalMethod1 0
         *  generalMethod1 1
         *  generalMethod1 2
         *  generalMethod2 0
         *  generalMethod2 1
         *  generalMethod2 2
         */
    }

    public synchronized void generalMethod1() {
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println("generalMethod1 " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void generalMethod2() {
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println("generalMethod2 " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
