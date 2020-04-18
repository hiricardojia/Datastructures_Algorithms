package DataStructures.Stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 *
 * @author Jia RenHao
 * @create 2020-04-05
 */
public class ArrayStack {
    public static void main(String[] args) {
        ArrStack stack = new ArrStack(5);
        String key;//保存用户输入
        boolean loop = true;//菜单结束判断
        Scanner input = new Scanner(System.in);
        while (loop) {
            System.out.println("show:显示当前栈信息");
            System.out.println("push:添加元素进栈");
            System.out.println("pop :元素出栈");
            System.out.println("exit:退出程序");
            key = input.next();
            switch (key){
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入添加的数：");
                    int value = input.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.println("出栈元素："+result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    System.out.println("程序退出~");
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 数组栈
 */
class ArrStack {
    public int maxSize;//栈的大小
    public int top = -1;//栈顶，默认-1
    public int[] stack;//数组栈

    public ArrStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     * @return 满则返回true
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     * @return -1为空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param value 入栈元素
     */
    public void push(int value){
        if (isFull()) {
            System.out.println("栈满，无法添加");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return 返回出栈元素
     */
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void showStack(){
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack["+i+"]:"+stack[i]);
        }
    }
}