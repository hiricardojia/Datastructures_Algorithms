package DataStructures.LinkedList;

import java.util.LinkedList;

/**
 * 双向链表
 *
 * @auther Jia RenHao
 * @create 2020-04-04
 */
public class doubleLinkedList {
    public static void main(String[] args) {
//创建英雄节点
        HeroNode2 yasuo = new HeroNode2(1, "疾风剑豪", "亚索");
        HeroNode2 Lee = new HeroNode2(2, "盲僧", "李青");
        HeroNode2 zed = new HeroNode2(4, "影流之主", "劫");
        HeroNode2 riven = new HeroNode2(3, "放逐之刃", "锐雯");
        //HeroNode2 riven1 = new HeroNode2(3, "放逐之刃", "锐雯1");//测试id冲突
        HeroNode2 vayne = new HeroNode2(5, "暗夜猎手", "薇恩");
        //创建英雄链表
        douLinkedList heroList = new douLinkedList();
        heroList.addNodeById(yasuo);
        heroList.addNodeById(Lee);
        heroList.addNodeById(riven);
        heroList.addNodeById(zed);
        //heroList.addNodeById(riven1);
        heroList.addNodeById(vayne);
        heroList.showNode();
        //测试双向链表根据id插入的前后节点
        System.out.println(vayne.pre + ":" + vayne.next);
    }
}

class douLinkedList {
    //定义头结点
    private HeroNode2 headNode2 = new HeroNode2(0, "", "");

    //获取头结点
    public HeroNode2 getHeadNode2() {
        return headNode2;
    }

    /**
     * 获取链表长度
     *
     * @return
     */
    public int linkedListLength2() {
        if (headNode2.next == null) {
            System.out.println("链表为空~~~");
            return 0;
        }
        int length = 0;
        HeroNode2 temp = headNode2;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 添加英雄节点，此时不考虑添加顺序,直接在尾节点添加
     * 1.遍历节点至最后一个节点
     * 2.将最后一个节点next指向新节点
     * 3.将新节点的pre设置为当前最后一个节点
     *
     * @param newNode
     */
    public void addNode(HeroNode2 newNode) {
        //因为头结点不能动，用temp辅助节点作为移动指针
        HeroNode2 temp = headNode2;
        while (temp.next != null) {
            //找到最后一个节点，返回
            temp = temp.next;
        }
        //此时已经找到链表最后一个节点，将新节点作为最后一个节点的next即添加成功
        temp.next = newNode;
        newNode.pre = temp;
    }

    /**
     * 通过英雄id排名进行添加节点到指定位置，如果没有则添加，如果排名冲突，则添加失败
     *
     * @param newNode
     */
    public void addNodeById(HeroNode2 newNode) {
        //创建辅助节点temp
        HeroNode2 temp = headNode2;
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
            //原节点的下一个节点连接到新节点的next上
            newNode.next = temp.next;
            //新节点作为原节点的下一个节点的pre
            if (temp.next != null) {//如果是最后一个，temp.next为空，temp.next.pre会报空指针
                temp.next.pre = newNode;
            }
            //新节点作为原节点的next
            temp.next = newNode;
            //原节点作为新节点的pre
            newNode.pre = temp;
        }
    }

    /**
     * 删除英雄节点
     * 和单链表有些许不同
     * 1.对于双向链表我们可以直接找到要删除的这个节点
     * 2.找到后，自我删除即可
     *
     * @param heroID
     */
    public void delNode(int heroID) {
        if (headNode2.next == null) {
            //说明链表为空
            System.out.println("链表为空，无法删除~~");
            return;
        }
        //创建temp
        HeroNode2 temp = headNode2.next;
        //创建标识
        boolean flag = false;//默认为没找到
        while (true) {
            if (temp == null) {
                //遍历结束没找到
                break;
            } else if (temp.id == heroID) {
                //找到了要删除的英雄id
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //遍历结束
        if (flag) {
            //temp.next = temp.next.next;//单链表
            //把当前要删除的节点next给上一个节点的next即可
            temp.pre.next = temp.next;
            //如果是最后一个节点就无需执行下面这句话，因为temp.next就是空的，所以temp.next.pre会报报空指针
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("未找到您要删除的英雄信息~~");
        }
    }

    /**
     * 修改英雄的名字和昵称，根据id进行修改，所以id不能修改
     * 和单链表一样，无序改动
     *
     * @param newNode
     */
    public void update(HeroNode2 newNode) {
        //判断链表是否为空
        if (headNode2.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //创建辅助指针
        HeroNode2 temp = headNode2;
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
     * 展示链表信息
     */
    public void showNode() {
        if (headNode2.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //用temp作为辅助节点
        HeroNode2 temp = headNode2.next;
        while (temp != null) {
            //到最后一个节点
            //输入信息
            System.out.println(temp);
            //节点后移
            temp = temp.next;
        }
    }
}


/**
 * 英雄节点类,双链表
 */
class HeroNode2 {
    public int id;//英雄id
    public String nickName;//英雄昵称
    public String name;//英雄名
    public HeroNode2 next;//下一个节点
    public HeroNode2 pre;//上一个节点

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode2(int id, String nickName, String name) {
        this.id = id;
        this.nickName = nickName;
        this.name = name;
    }
}