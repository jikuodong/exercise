package day01;

/**
 *
 * 多线程程的创建：方式一：继承与Thread类
 *
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() ----> 将此线程执行的操生命在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()
 *
 *
 * 例子： 遍历100以内的所有的偶数
 *
 */

// 1.创建一个继承于Thread类的子类
class MyThread extends Thread{
    // 2. 重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":"  + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        // 3. 创建Thread类的子类的对象
        MyThread myThread = new MyThread();

        // 4. 通过此对象调用start(): ① 启动当前线程 ② 调用当前线程的run()
        myThread.start();

        // 问题一： 我们不能通过直接调用run()的方式启动线程
        //  myThread.run();

        // 问题二： 再启动一个线程，遍历100以内的偶数。 不可以还让已经start()的线程去执行。会报异常
//        myThread.start();

        // 我们需要重新创建一个线程对象
        MyThread myThread1 = new MyThread();
        myThread1.start();


        // 如下的操作仍然是在main线程中执行的。
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "**********************************");
            }
        }
    }
}
