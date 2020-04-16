package DataStructures.Stack;

/**
 * 数组链表实现计算器，中缀表达式
 * 此程序问题：两个减号会出现错误，例如：5-6*2-1，正确答案-8，这个算出-6，因为5-12-1，先算12-1，没有考虑到12前面的-号
 *
 * @auther Jia RenHao
 * @create 2020-04-05
 */
public class CalculatorTest {
    public static void main(String[] args) {
        //创建表达式
        String expression = "5-6*2-1";//2790
        new Calculator().scanningExpr(expression);
    }
}

class Calculator {
    //创建表达式
    public String expression = null;
    //数栈
    public ArrStack2 numStack;
    //符号栈
    public ArrStack2 operStack;

    public Calculator() {
        numStack = new ArrStack2(10);
        operStack = new ArrStack2(10);
    }

    /**
     * 计算逻辑
     *
     * @param expression 要扫描的表达式
     */
    public void scanningExpr(String expression) {
        int index = 0;//用于索引
        int num1;
        int num2;
        int oper;
        int result;
        String keepNum = "";//用于拼接多位数
        char ch;//存储当前扫描到的字符
        while (true) {
            //获取当前字符值
            ch = expression.charAt(index);
            //判断ch是什么
            if (operStack.isOper(ch)) {
                //判断符号栈内是否为空
                if (operStack.isEmpty()) {
                    //如果是空的，直接入栈
                    operStack.push(ch);
                } else {
                    //不是空的，进行下一步判断
                    //在符号栈中弹出一个符号，判断符号优先级，如果当前符号的优先级小于或等于栈中符号优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        //当前结果入数栈
                        numStack.push(result);
                        //当前操作符入栈
                        operStack.push(ch);
                    } else {
                        //优先级大，直接进栈
                        operStack.push(ch);
                    }
                }
            } else {
                //如果扫描到的是数
                //numStack.push(ch-48);//此时是ASCII  '1' => 1  减48

                //处理多位数
                keepNum += ch;
                //如果等于表达式长度，说明是最后一个
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字,如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //注意：此处已经判断下一个不是数字，所以已经进栈，要清空keepNum
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index == expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就按顺序从数栈和符号栈中pop出响应的数和符号，并运行
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        //将数栈最后的数弹出，就是最后结果
        int res = numStack.pop();
        System.out.println("表达式" + expression + "=" + res);
    }
}

/**
 * 数组栈
 */
class ArrStack2 {
    public int maxSize;//栈的大小
    public int top = -1;//栈顶，默认-1
    public int[] stack;//数组栈

    public ArrStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     *
     * @return 满则返回true
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     *
     * @return -1为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value 入栈元素
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法添加");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return 返回出栈元素
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 查看当前栈顶元素
     *
     * @return 当前栈顶元素
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 遍历栈
     */
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "]:" + stack[i]);
        }
    }

    /**
     * 判断符号的有现金，数字越大，优先级越高
     *
     * @param oper 符号
     * @return 优先级数字
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是不是符号
     *
     * @param val 当前字符
     * @return 是符号true，不是false
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;//注意弹栈顺序
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}