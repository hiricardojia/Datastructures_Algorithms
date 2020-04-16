package DataStructures.Stack;

/**
 * 链表模拟栈
 *
 * @auther Jia RenHao
 * @create 2020-04-05
 */
public class LinkedListStack {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Node4Stack node4Stack1 = new Node4Stack(10);
        Node4Stack node4Stack2 = new Node4Stack(20);
        Node4Stack node4Stack3 = new Node4Stack(30);
        Node4Stack node4Stack4 = new Node4Stack(40);
        linkedList.push(node4Stack1);
        linkedList.push(node4Stack2);
        linkedList.push(node4Stack3);
        linkedList.push(node4Stack4);
        linkedList.showLinkedListStack();
        System.out.println("当前栈长度"+linkedList.getSize());
        System.out.println("测试出栈");
        System.out.println("当前出栈节点："+linkedList.pop());
        System.out.println("当前出栈节点："+linkedList.pop());
        System.out.println("当前出栈节点："+linkedList.pop());
        System.out.println("当前出栈节点："+linkedList.pop());
        System.out.println("------");
        linkedList.showLinkedListStack();
        System.out.println("当前栈长度"+linkedList.getSize());
    }
}

/**
 * 链表栈
 */
class LinkedList{
    private Node4Stack headNode;//头节点
    private int size;//当前栈长度

    public LinkedList() {
        headNode = new Node4Stack(-1);
    }

    public boolean isEmpty(){
        return headNode.next == null;
    }

    /**
     * 入栈，采用头插法
     * @param newNode 要插入的新节点
     */
    public void push(Node4Stack newNode){
        //判断是否是第一个节点，如果是第一个，直接插入
        if (headNode.next == null) {
            headNode.next = newNode;
            size++;
            return;
        }
        //头插法
        //创建temp记录头节点的next
        Node4Stack tempNode = headNode.next;
        //把第一个节点作为新节点的下一个节点
        newNode.next = tempNode;
        //把新节点作为头结点的下一个节点
        headNode.next = newNode;
        //栈长度加一
        size++;
    }

    /**
     * 出栈
     * @return 返回出栈节点
     */
    public Node4Stack pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空~~");
        }
        Node4Stack tempNode = headNode.next;
        headNode.next = tempNode.next;
        size--;
        return tempNode;
    }

    public void showLinkedListStack(){
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        Node4Stack tempNode = headNode.next;
        while (tempNode != null) {
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }

    public int getSize(){
        return size;
    }
}

/**
 * int型节点
 */
class Node4Stack{
    public int value;//要保存的值
    public Node4Stack next;//下一个节点

    public Node4Stack(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node4Stack{" +
                "value=" + value +
                '}';
    }
}