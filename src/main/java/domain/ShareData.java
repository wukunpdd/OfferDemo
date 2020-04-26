package domain;

/**
 * @Author wukun
 * @Date 2020/4/26 14:41
 */
public class ShareData {
    public static int data = 0;

    public synchronized void addData(int num) {
        data += num;
        System.out.println(Thread.currentThread().getName() + "对data + " + num +"后，data:" + data);
    }

    public synchronized void delData(int num) {
        data -= num;
        System.out.println(Thread.currentThread().getName() + "对data - " + num +"后，data:" + data);
    }

    public int getData() {
        return data;
    }
}
