package DataStructures.queue;

import java.util.Scanner;

/**
 * 数组模拟循环队列
 *
 * @author Jia RenHao
 * @create 2020-04-02
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArrQueue queue = new CircleArrQueue(4);//这里设置4，说明队列最大只能放三个元素
        char key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                        break;
                    } catch (Exception e) {
                        e.getMessage();
                    }
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");

    }
}

class CircleArrQueue {
    private int maxSize;//队列最大长度
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front初始值=0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear初始值=0
    private int rear;
    private int[] arr;//队列数组

    public CircleArrQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否空
    public boolean isEmpty() {
        return rear == front;
    }

    //插入数据到队列
    public void addQueue(int n) {
        //判断队列是否为满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        //直接将数据插入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //取出队列中的元素，出队列
    public int getQueue() {
        //判断是否为空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里分析出front是指向队列的第一个元素
        //1.先把front的值保存在一个临时变量中
        //2.将front后移，考虑取模
        //3.返回临时变量
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有的数据
    public void showQueue() {
        //判断是否为空
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        //遍历
        //思路：从front开始，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //获得当前队列的有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}