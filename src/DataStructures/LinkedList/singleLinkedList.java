package DataStructures.LinkedList;

import java.util.Stack;

/**
 * 单链表
 *
 * @auther Jia RenHao
 * @create 2020-04-03
 */
public class singleLinkedList {
    public static void main(String[] args) {
        //创建英雄节点
        HeroNode yasuo = new HeroNode(1, "疾风剑豪", "亚索");
        HeroNode Lee = new HeroNode(2, "盲僧", "李青");
        HeroNode zed = new HeroNode(4, "影流之主", "劫");
        HeroNode riven = new HeroNode(3, "放逐之刃", "锐雯");
        //HeroNode riven1 = new HeroNode(3, "放逐之刃", "锐雯1");//测试id冲突
        HeroNode vayne = new HeroNode(5, "暗夜猎手", "薇恩");
        //创建英雄链表
        sinLinkedList heroList = new sinLinkedList();
        heroList.addNodeById(yasuo);
        heroList.addNodeById(Lee);
        heroList.addNodeById(riven);
        heroList.addNodeById(zed);
        //heroList.addNodeById(riven1);
        heroList.addNodeById(vayne);
        heroList.showNode();
        //测试修改英雄信息
        System.out.println("---修改锐雯为锐萌萌---");
        heroList.update(new HeroNode(3, "放逐之刃", "锐萌萌"));
        heroList.showNode();
        //测试删除英雄信息
        System.out.println("---删除id为1的英雄信息---");
        heroList.delNode(1);
        heroList.showNode();
        //测试获取链表长度
        System.out.println("---获取链表长度---");
        System.out.println("链表长度：" + heroList.linkedListLength());
        //测试查询倒数第index个节点
        System.out.println("---测试查询倒数index的节点信息---");
        System.out.println(heroList.findLastIndexNode(1));
        //测试链表的反转
        //System.out.println("---测试链表的反转---");
        //heroList.reverseLinkedList();
        //heroList.showNode();
        //测试逆序遍历
        System.out.println("---测试逆序遍历---");
        heroList.reversePrint();
    }
}

/**
 * 定义链表，管理英雄们
 */
class sinLinkedList {
    //定义头结点，保存头指针，不轻易变动
    private HeroNode headNode = new HeroNode(0, "", "");

    /**
     * 返回链表长度
     *
     * @return
     */
    public int linkedListLength() {
        int length = 0;
        if (headNode.next == null) {
            System.out.println("链表为空~~");
            return length;
        }
        //创建temp
        HeroNode temp = headNode;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 添加英雄节点，此时不考虑添加顺序,直接在为节点添加
     * 1.遍历节点至最后一个节点
     * 2.将最后一个节点next指向新节点
     *
     * @param newNode
     */
    public void addNode(HeroNode newNode) {
        //因为头结点不能动，用temp辅助节点作为移动指针
        HeroNode temp = headNode;
        while (temp.next != null) {
            //找到最后一个节点，返回
            temp = temp.next;
        }
        //此时已经找到链表最后一个节点，将新节点作为最后一个节点的next即添加成功
        temp.next = newNode;
    }

    /**
     * 通过英雄id排名进行添加节点到指定位置，如果没有则添加，如果排名冲突，则添加失败
     *
     * @param newNode
     */
    public void addNodeById(HeroNode newNode) {
        //创建辅助节点temp
        HeroNode temp = headNode;
        //创建一个标识，记录id排名是否重复
        boolean flag = false;//默认为false，没有重复
        while (true) {
            if (temp.next == null) {
                //说明添加的节点id最大，已经遍历到最后
                break;
            } else if (temp.next.id > newNode.id) {
                //此处判断原始链表下一个id比新节点id大，说明找到了插入位置
                break;
            } else if (temp.next.id == newNode.id) {
                //此处说明排名重复，将flag置为true
                flag = true;
            }
            //继续遍历下一节点
            temp = temp.next;
        }
        //结束遍历
        if (flag) {
            System.out.println("您要插入的英雄" + newNode.name + "排名重复，无法插入");
        } else {
            //如果不重复，则进行插入
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    /**
     * 修改英雄的名字和昵称，根据id进行修改，所以id不能修改
     *
     * @param newNode
     */
    public void update(HeroNode newNode) {
        //判断链表是否为空
        if (headNode.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //创建辅助指针
        HeroNode temp = headNode;
        //创建一个标识记录是否找到要修改的英雄
        boolean flag = true;//默认为true，默认能找到
        while (true) {
            if (temp.next == null) {
                //遍历结束，说明没找到
                flag = false;
                break;
            } else if (temp.next.id == newNode.id) {
                //说明找到了要修改的英雄id,跳出进行下一步操作
                break;
            }
            temp = temp.next;
        }
        //遍历结束后
        if (flag) {
            //说明找到了
            temp.next.name = newNode.name;
            temp.next.nickName = newNode.nickName;
        } else {
            System.out.println("没有找到要修改的ID为" + newNode.id + "信息");
        }
    }

    /**
     * 删除英雄节点
     * @param heroID
     */
    public void delNode(int heroID) {
        if (headNode.next == null) {
            //说明链表为空
            System.out.println("链表为空，无法删除~~");
            return;
        }
        //创建temp
        HeroNode temp = headNode;
        //创建标识
        boolean flag = false;//默认为没找到
        while (true) {
            if (temp.next == null) {
                //遍历结束没找到
                break;
            } else if (temp.next.id == heroID) {
                //找到了要删除的英雄id
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //遍历结束
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("未找到您要删除的英雄信息~~");
        }
    }

    /**
     * 展示链表信息
     */
    public void showNode() {
        if (headNode.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //用temp作为辅助节点
        HeroNode temp = headNode.next;
        while (temp != null) {
            //到最后一个节点
            //输入信息
            System.out.println(temp);
            //节点后移
            temp = temp.next;
        }
    }

    /**
     * 新浪面试题
     * 根据索引index，查找倒数第index个节点信息
     *
     * @return
     */
    public HeroNode findLastIndexNode(int index) {
        if (headNode.next == null) {
            System.out.println("链表为空~~");
            return null;
        }
        //创建temp
        HeroNode temp = headNode.next;
        //获得链表长度
        int size = linkedListLength();
        //对index进行校验
        if (index < 0 || index > size) {
            System.out.println("index不合法");
            return null;
        }
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 腾讯面试题：链表反转，将链表倒过来，此处头插法
     * 思路：
     * 1.创建一个辅助链表头，临时连接反转好的链表
     * 2.创建一个临时节点，记录当前节点的下一个节点（因为将当前节点断开，会丢失下一个节点的指针）
     * 3.再将原链表头节点指向新链表的首节点
     *
     * @return
     */
    public void reverseLinkedList() {
        //如果当前链表为空或者只有一个节点则无需反转
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        //创建temp，保存当前链表的一个节点
        HeroNode temp = headNode.next;
        //创建next节点保存temp的下一个节点信息
        HeroNode next;
        //创建一个临时链表头节点，作为新链表的中转头节点
        HeroNode tempReverseHead = new HeroNode(0, "", "");
        while (temp != null) {
            //将当前节点的下一个节点存到next中间节点保存
            next = temp.next;
            //核心步骤：断开了当前节点和下一个节点的连接，将下一个节点的next设置为当前节点，即完成了两个节点的反转
            temp.next = tempReverseHead.next;
            //将当前节点连接到临时链表的头节点上
            tempReverseHead.next = temp;
            //当前节点后移
            temp = next;
        }
        //反转完成，将原链表的头指针指回来
        headNode.next = tempReverseHead.next;
    }

    /**
     * 百度面试题
     * 逆序遍历链表
     * 思路：借助栈的先进后出
     */
    public void reversePrint() {
        if (headNode.next == null) {
            System.out.println("链表为空~~");
        }
        //创建temp
        HeroNode temp = headNode.next;
        //创建栈
        Stack<HeroNode> heroNodes = new Stack<>();
        //压栈
        while (temp != null) {
            heroNodes.push(temp);
            temp = temp.next;
        }
        //出栈
        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());
        }
    }
}

/**
 * 英雄节点类
 */
class HeroNode {
    public int id;//英雄id
    public String nickName;//英雄昵称
    public String name;//英雄名
    public HeroNode next;//下一个节点

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int id, String nickName, String name) {
        this.id = id;
        this.nickName = nickName;
        this.name = name;
    }
}