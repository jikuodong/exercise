package day04;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * Collections: 操作Collection、Map的工具类
 *
 *
 */
public class CollectionsTest {
    BlockingQueue queue  = new LinkedBlockingQueue();
    @Test
    public void test1(){
        List list = new ArrayList();
        list.add(123);
        list.add(43);
        list.add(125);
        list.add(534);
        System.out.println(list);
        Collections.replaceAll(list,123,111);
        System.out.println(list);

    }

    // 测试冒泡排序
    @Test
    public void test2() {
        int[] arr = {1, 20, 40, 50,100, 90};
        for (int i = 0; i<arr.length-1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int a: arr) {
            System.out.println(a);
        }
    }
    // 测试选择排序
    @Test
    public void test3() {
        int[] arr = {1, 20, 40, 50,100, 90};
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++){
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]){
                    min = j;
                }
            }
            // 交换
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
    int a = 0;
    @Test
    public void test4() throws InterruptedException {
        // 获取定时任务线程池
        ScheduledExecutorService scheduledExec = getScheduledThreadExecutor();
        scheduledExec.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(++a);
                for (int i = 0; i < 100; i++) {
                    Person person1 = new Person("测试" + i, 12);
                    Person person2 = new Person("测试" + i, 13);
                    Map map = new HashMap();
                    map.put("session", person1);
                    map.put("customPack", person2);
                    queue.offer(map);
                }
            }
        }, 5, 3, TimeUnit.SECONDS);
        ExecutorService exec = getExecutorService();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(new Date());
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Test
    public void test5() {
        ExecutorService exec = getExecutorService();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        System.out.println(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        for (int i = 0; i < 100; i++) {
//            Person person1 = new Person("测1试" + i, 12);
//            Person person2 = new Person("测2试" + i, 13);
//            Map map = new HashMap();
//            map.put("session", person1);
//            map.put("customPack", person2);
//            queue.offer(map);
//        }
    }
    // 可缓存的线程池
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    // 定时任务线程池
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);

    // 获取线程池
    public static ExecutorService getExecutorService() {
        return executorService;
    }

    // 获取定时任务线程池
    public static ScheduledExecutorService getScheduledThreadExecutor() {
        return scheduledExecutor;
    }
}
