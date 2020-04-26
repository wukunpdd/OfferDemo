package concurrency.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author wukun
 * @Date 2020/4/26 11:18
 */
public class ReadWriteLockDemo {

    private final Map<String, Object> cache = new HashMap<String, Object>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock(); //定义读锁
    private final Lock writeLock = rwLock.writeLock(); //定义写锁

    //在读取数据时加锁
    private Object getName(String name) {
        readLock.lock();
        try {
            return cache.get(name);
        }finally {
            readLock.unlock();
        }
    }

    //在写数据时加锁
    private void put(String name, Object value) {
        writeLock.lock();
        try {
            cache.put(name, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        demo.put("name", "吴堃");
        String name = (String)demo.getName("name");
        System.out.println(name);
    }
}
