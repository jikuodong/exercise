package exceptiontest;

/**
 * @projectName: java
 * @package: exceptiontest
 * @className: Myexception
 * @author: JKD
 * @description:
 * @date: 2020/7/8 16:32
 * @version: 1.0
 */
public class Myexception extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public Myexception() {
    }
    public Myexception(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "错误代码："  + "，" + getMessage();
    }
}
