package cookie;

import java.util.Scanner;

/**
 * @projectName: java
 * @package: cookie
 * @className: Test
 * @author: JKD
 * @description: 测试类
 * @date: 2020/1/19 9:38
 * @version: 1.0
 */
public class Test {
    /**
     * @description
     *
     * @method main
     * @author JKD
     * @param args
     * @return void
     * @Exception
     * @date 2020/1/19 16:10
     */
    public static void main(String[] args) {
        boolean x = true;
        boolean y = false;
        short z =42;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入1");
        System.out.println(scanner.next());
        System.out.println("请输入2");
        System.out.println(scanner.next());
        int i = (int) Math.random()*10;
        System.out.println(i);
//        if (y == true)
        if ((z++ == 42) && (y==true)) {
            z++;
        }
        if ((x=false)||(++z == 45)) {
            z++;
        }
        System.out.println("z=" + z);
    }
}
