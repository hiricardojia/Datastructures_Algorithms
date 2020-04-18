package DataStructures.Stack;

import java.util.*;

/**
 * 逆波兰表达式实现计算器
 *
 * @author Jia RenHao
 * @create 2020-04-06
 */
public class PolandNotation {
    public static void main(String[] args) {
        //定义一个后缀表达式
        //String expression = "30 4 + 5 * 6 -";
        //System.out.println("结果是：" + calculate(getListString(expression)));
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpr = toInfixExpressionList(expression);
        parseInfixToSuffixExpression(infixExpr);
        System.out.println(parseInfixToSuffixExpression(infixExpr));
        System.out.println("结果是：" + calculate(parseInfixToSuffixExpression(toInfixExpressionList(expression))));
    }

    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //利用正则表达式取数
            if (item.matches("\\d+")) {//取出的是多位数
                stack.push(item);
            } else {
                //pop出两个数，先运算，结果再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result;
                switch (item) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num2 - num1;//注意弹栈顺序
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push("" + result);//转换成String
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infixExpressionList 已经分割好的前缀表达式
     * @return 去掉括号的后缀表达式
     */
    public static List<String> parseInfixToSuffixExpression(List<String> infixExpressionList) {
        Stack<String> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        for (String item : infixExpressionList) {
            //如果是数字直接加入list
            if (item.matches("\\d+")) {
                result.add(item);
            } else if ("(".equals(item)) {
                //左括号直接入栈
                stack.push(item);
            } else if (")".equals(item)) {
                //如果是右括号，就从栈中一直弹出，直到peek到左括号
                while (!"(".equals(stack.peek())) {
                    result.add(stack.pop());
                }
                //把“（”弹栈，“）”不做处理，至此括号消除完毕
                stack.pop();
            } else {
                //判断优先级
                //如果item的优先级小于等于栈顶符号优先级，则栈中符号先弹栈，然后把item入栈
                while (stack.size() != 0 && Operation.getValue(item) <= Operation.getValue(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(item);
            }
        }
        //将stack中剩余元素弹出加入list
        while (stack.size() != 0) {
            result.add(stack.pop());
        }
        return result;
    }

    /**
     * 将中缀表达式分隔
     *
     * @param infixExpression 要分隔的中缀表达式
     * @return 分隔后的list<String>集合
     */
    public static List<String> toInfixExpressionList(String infixExpression) {
        //定义一个List存放中缀表达式的内容，方便遍历
        List<String> list = new ArrayList<>();
        int index = 0;//游标指针，用于遍历中缀表达式字符串
        StringBuilder str;//用于拼接多位数的字符串
        char ch;//每遍历到一个字符就放到ch中
        while (index < infixExpression.length()) {
            //判断ch不是数字
            if ((ch = infixExpression.charAt(index)) < 48 || (ch = infixExpression.charAt(index)) > 57) {
                list.add("" + ch);
                index++;
            } else {
                //判断是一个数，考虑多位数
                str = new StringBuilder();
                while (index < infixExpression.length() && infixExpression.charAt(index) >= 48 && (ch = infixExpression.charAt(index)) <= 57) {
                    str.append(ch);//拼接
                    index++;
                }
                list.add(str.toString());
            }
        }
        return list;
    }

    /**
     * 将一个表达式的数字和符号单独分隔
     *
     * @param suffixExpression 原始逆波兰表达式
     * @return 返回分隔好的字符串
     */
    public static List<String> getListString(String suffixExpression) {
        //放到ArrayList中，方便遍历
        List<String> list = new ArrayList<>();
        //对表达式按空格进行分隔
        String[] split = suffixExpression.split(" ");
        for (String item : split) {
            list.add(item);
        }
        return list;
    }
}

/**
 * 编写一个类 Operation 可以返回一个运算符 对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                //System.out.println("不存在该运算符" + operation);
                result = -1;
                break;
        }
        return result;
    }
}
