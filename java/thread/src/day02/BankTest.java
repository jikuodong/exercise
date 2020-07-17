package day02;

/**
 * @projectName: java
 * @package: day02
 * @className: BankTest
 * @author: JKD
 * @description: 使用同步机制将单例模式中的懒汉式改为线程安全的
 * @version: 1.0
 */
public class BankTest {
    public static void main(String[] args) {
    }
}

class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    // 同步方法
//    public static synchronized Bank getInstance(){
//        if (instance == null){
//            instance = new Bank();
//        }
//        return instance;
//    }
    // 同步代码块
    public static Bank getInstance() {
        // 方式一： 效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }
        // 方式二：效率更高
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }

}
