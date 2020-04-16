package DataStructures.LinkedList;

/**
 * 单向循环链表_约瑟夫问题
 *
 * @auther Jia RenHao
 * @create 2020-04-04
 */
public class Joseph_circleSingleLinkedList {
    public static void main(String[] args) {
        CirSingleLinkedList cirSingleLinkedList = new CirSingleLinkedList();
        cirSingleLinkedList.addChileNode(5);
        cirSingleLinkedList.showChildren();
        cirSingleLinkedList.outCircle(1,2,5);
    }
}

/**
 * 创建一个环形单链表
 */
class CirSingleLinkedList {
    private Child first = null;//头结点，记录第一个孩子,此处是环形链表，所以first可移动

    /**
     * 添加孩子节点
     *
     * @param num   一共有几个小孩围成圈
     */
    public void addChileNode(int num) {
        //对参数的合理性进行判断
        if (num < 1) {
            System.out.println("输入的num不合法~~");
            return;
        }
        //创建curChild节点，本质是尾节点
        Child curChild = null;
        for (int i = 1; i <= num; i++) {
            Child child = new Child(i);
            //如果是第一个节点
            if (i == 1) {
                first = child;
                child.setNext(first);//构成环
                curChild = first;//指向第一个节点
            } else {
                curChild.setNext(child);//断开指向的first，指向新节点
                child.setNext(first);//新节点的下一个指向first
                curChild = child;//尾指针后移
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showChildren() {
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        //创建尾指针curChild
        Child curChild = first;
        while (true) {
            System.out.println("当前小孩的编号是：" + curChild.getId());
            //当前节点的下一个是first说明遍历完成
            if (curChild.getNext() == first) {
                break;
            }
            curChild = curChild.getNext();
        }
    }

    /**
     * 约瑟夫问题实现
     * 根据用户输入，计算小孩出圈顺序
     *
     * @param start       表示从第几个小孩开始计数
     * @param count       表示一次数几下
     * @param childrenNum 表示一共有多少个小孩
     */
    public void outCircle(int start, int count, int childrenNum) {
        //对用户输入进行校验
        if (first == null || start < 1 || start > count) {
            System.out.println("输入参数有误~~");
            return;
        }
        //创建辅助节点记录最后一个节点,通过遍历找到，所以从first开始
        Child last = first;
        while (last.getNext() != first) {//说明last已经指向到了最后一个节点
            last = last.getNext();
        }
        //开始前要把first和last指向到要开始的孩子，所以移动start-1次
        for (int i = 0; i < start - 1; i++) {
            first = first.getNext();
            last = last.getNext();
        }
        //当开始报数时，first和last同时移动count-1个,然后进行出圈
        //last == first说明圈中只剩一个节点
        while (last != first) {
            for (int i = 0; i < count - 1; i++) {
                first = first.getNext();
                last = last.getNext();
            }
            //此时first节点就是要出圈小孩节点
            System.out.println("小孩" + first.getId() + "出圈");
            //开始出圈操作
            //将first指向下一个节点
            first = first.getNext();
            last.setNext(first);
        }
        System.out.println("最后一个留在圈中的小孩编号是"+first.getId());
    }
}

/**
 * 创建小孩实体类节点
 */
class Child {
    private int id;//小孩编号
    private Child next;//小孩的下一个节点

    public Child(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }

}