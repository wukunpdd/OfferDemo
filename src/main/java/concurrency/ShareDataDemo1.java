package concurrency;

import domain.ShareData;

/**
 * 多线程共享数据
 *  方法一：将数据抽象成一个类，并将对这个数据的操作封装在类的方法中
 *
 * @Author wukun
 * @Date 2020/4/26 14:19
 */
public class ShareDataDemo1 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        AddRunnable addRunnable = new AddRunnable(shareData);
        DelRunnable delRunnable = new DelRunnable(shareData);

        for(int i = 0; i < 5; i++) {
            new Thread(addRunnable).start();
            new Thread(delRunnable).start();
        }
    }
}

//内部类不能使用public、protected、private修饰符
class AddRunnable implements Runnable {
    ShareData shareData;

    public AddRunnable(ShareData shareData) {
        this.shareData = shareData;
    }

    public void run() {
        shareData.addData(3);
    }
}

class DelRunnable implements Runnable {

    ShareData shareData;

    public DelRunnable(ShareData shareData){
        this.shareData = shareData;
    }

    public void run() {
        shareData.delData(2);
    }
}
