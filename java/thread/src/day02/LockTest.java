package day02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: java
 * @package: day02
 * @className: LockTest
 * @author: JKD
 * @description: 解决线程安全问题的方式三： Lock锁 ----JDK5.0新增
 *
 *  1. 面试题： synchronized 与Lock 的异同？
 *      相同： 二者都可以解决线程安全问题。
 *      不同： synchronzied 机制在执行完相应的同步代码以后，自动的释放同步监视器
 *              lock需要手动的启动同步（lock()）,同时结束同步也需要手动的实现（unlock()）
 *
 * 2.如何解决线程安全问题？有几种方式
 *  1.同步代码块
 *  2.同步方法
 *  3.lock
 *
 *
 *
 * @version: 1.0
 */

class Window implements Runnable{
    private int ticket = 100;
    private int i = 1;
    // 1.实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            // 2. 调用锁定方法lock()
            lock.lock();
            try {
                if (ticket>0) {
                    lock.lock();
                    try {
                        Thread.sleep(100);
                        System.out.println(i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    System.out.println(Thread.currentThread().getName()+ ": 售票， 票号为：" + ticket) ;
                    ticket--;
                }else {
                    break;
                }
            } finally {
                // 3. 调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
