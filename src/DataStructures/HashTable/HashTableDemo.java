package DataStructures.HashTable;

import java.util.Scanner;

/**
 * 哈希表
 * 实现雇员在哈希表中的存储
 *
 * @author Jia RenHao
 * @create 2020-04-15
 */
public class HashTableDemo {
    public static void main(String[] args) {
        EmpHashTable hashTable = new EmpHashTable(7);
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del : 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Employee Employee = new Employee(id, name);
                    hashTable.addEmp(Employee);
                    break;
                case "list":
                    hashTable.showHashTable();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.delEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class EmpHashTable {
    private final EmpLinkedList[] EmpLinkedListArray;
    private final int size;

    public EmpHashTable(int size) {
        this.size = size;
        EmpLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            EmpLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void addEmp(Employee emp) {
        int hashCode = hashFun(emp.getId());
        EmpLinkedListArray[hashCode].addEmp(emp);
    }

    public void showHashTable() {
        for (int i = 0; i < size; i++) {
            EmpLinkedListArray[i].showEmpLinkedList(i);
        }
    }

    public void findEmpById(int id) {
        int hashCode = hashFun(id);
        Employee emp = EmpLinkedListArray[hashCode].findEmpById(id);
        if (emp == null) {
            System.out.println("表中没有该雇员信息~~");
        } else {
            System.out.println("在第" + hashCode + "表中找到该雇员信息" + emp);
        }
    }

    public void delEmpById(int id) {
        int hashCode = hashFun(id);
        int flag = EmpLinkedListArray[hashCode].delEmpById(id);
        if (flag > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("未找到您要删除的雇员");
        }
    }

    public int hashFun(int id) {
        return id % size;
    }
}

class EmpLinkedList {
    private Employee head;
    private Employee curEmp;

    public void addEmp(Employee emp) {
        if (head == null) {
            head = emp;
        } else {
            curEmp = head;
            while (curEmp.next != null) {
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
    }

    public void showEmpLinkedList(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空~~");
        } else {
            curEmp = head;
            System.out.println("第" + no + "条链表信息为：");
            while (true) {
                System.out.println(curEmp + "\t");
                if (curEmp.next == null) {
                    break;
                }
                curEmp = curEmp.next;
            }
        }
    }

    public Employee findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        } else {
            curEmp = head;
            while (true) {
                if (curEmp.getId() == id) {
                    return curEmp;
                } else if (curEmp.next == null) {
                    return null;
                }
                curEmp = curEmp.next;
            }
        }
    }

    public int delEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空~~");
            return -1;
        } else {
            curEmp = head;
            while (true) {
                if (curEmp.next.getId() == id) {
                    curEmp.next = curEmp.next.next;
                    return 1;
                } else if (curEmp.next == null) {
                    return -1;
                }
                curEmp = curEmp.next;
            }
        }
    }
}

class Employee {
    private int id;
    private String name;
    public Employee next;

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}