package day02;

/**
 * 例子： 创建三个窗口卖票，总数为100张. 使用继承Thread类的方式
 * 同步机制，来解决线程的安全问题。
 *
 * 方式一：
 * synchronized(同步监视器) {
 *     // 需要被同步的代码
 * }
 *
 * 说明：  1.操作共享数据的代码，即为需要被同步的代码
 *          2.共享数据：多个线程共同操作的变量。比如： ticket就是共享数据
 *          3.同步监视器： 俗称：锁。任何一个类的对象，都可以充当锁。
 *              要求： 多个线程必须要用同一把锁。
 *
 * 方式二：同步方法
 *    如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明为同步的。
 *
 *
 * 同步的方式，解决了线程的安全问题。----好处
 *   操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。 -----------坏处
 *
 *
 *
 */

class Window1 implements Runnable{
    private static int ticket = 100;
    final Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
//            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }

    }
}

public class WindowTest {

    public static void main(String[] args) {

        Window1 w = new Window1();

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
