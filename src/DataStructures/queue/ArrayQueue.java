package DataStructures.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 *
 * @author Jia RenHao
 * @create 2020-04-01
 */
public class ArrayQueue {
    public static void main(String[] args) {
        ArrQueue arrQueue = new ArrQueue(3);
        char key = ' ';//用户输入的字符
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(showQueue)：展示队列");
            System.out.println("a(addQueue)：添加元素");
            System.out.println("g(getQueue)：取出元素");
            System.out.println("h(headQueue)：显示队列首元素");
            System.out.println("e(exit)：退出程序");
            switch (scanner.next().charAt(0)) {
                case 's':
                    arrQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要插入的数字");
                    arrQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int queue = arrQueue.getQueue();
                        System.out.println("取出的数据是：" + queue + "\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("首元素是：" + arrQueue.headQueue() + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrQueue {
    private int maxSize;//队列最大长度
    private int front;//front表示队列头元素的前一个位置
    private int rear;//rear表示队列尾元素
    private int[] arr;//队列数组

    //队列构造器
    public ArrQueue(int arrmaxSize) {
        maxSize = arrmaxSize;
        front = -1;//分析出front是队列头的前一个位置
        rear = -1;//rear是队列尾的数据
        arr = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //判断队列是否为满
    public boolean isFull() {
        return rear == maxSize;
    }

    //向队列中添加元素
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满~~无法添加数据");
        }
        rear++;
        arr[rear] = n;
        //也可arr[++rear] = n; 注意++rear
    }

    //元素出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有元素可以出队列");
        }
        front++;//队首元素后移
        return arr[front];
    }

    //展示队列
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有元素");
        }
        for (int i = 0; i < rear + 1; i++) {
            System.out.printf("arr[%d] = %d\t", i, arr[i]);
        }
        System.out.println();
    }

    //显示front元素，并不是取出，只显示
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有元素");
        }
        return arr[front + 1];
    }
}