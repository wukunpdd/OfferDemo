package concurrency;

import domain.ShareData;

/**
 * 多线程共享数据
 *  方法二：将Runnable对象作为一个类的内部类，将共享数据作为这个类的成员变量
 *
 * @Author wukun
 * @Date 2020/4/26 14:38
 */
public class ShareDataDemo2 {

    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        for(int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                public void run() {
                    shareData.addData(4);
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    shareData.delData(2);
                }
            }).start();
        }
    }
}
