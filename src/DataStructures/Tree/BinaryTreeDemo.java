package DataStructures.Tree;

/**
 * 二叉树
 *
 * @auther Jia RenHao
 * @create 2020-04-16
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(1, "疾风剑豪");
        TreeNode node2 = new TreeNode(2, "影流之主");
        TreeNode node3 = new TreeNode(3, "放逐之刃");
        TreeNode node4 = new TreeNode(4, "盲僧");
        TreeNode node5 = new TreeNode(5, "无双剑姬");

        binaryTree.setRoot(root);
        root.setLeftNode(node2);
        root.setRightNode(node4);
        node4.setLeftNode(node3);
        node4.setRightNode(node5);
        /*
        //        1
        //     2     4
        //         3   5
        //前序遍历
        System.out.println("前序遍历");//12435
        binaryTree.proOrder();

        //中序遍历
        System.out.println("中序遍历");//21345
        binaryTree.infixOrder();

        //后序遍历
        System.out.println("后序遍历");//23541
        binaryTree.postOrder();*/
        TreeNode resNode;
        /*//先序查找 找4次
        System.out.println("先序查找");
        resNode = binaryTree.preOrderSearch(3);
        if (resNode != null) {
            System.out.println(resNode);
        }else {
            System.out.println("没找到该节点");
        }*/

        /*//中序查找 找3次
        System.out.println("中序查找");
        resNode = binaryTree.infixOrderSearch(3);
        if (resNode != null) {
            System.out.println(resNode);
        }else {
            System.out.println("没找到该节点");
        }*/

        //后序查找 找2次
        System.out.println("后序查找");
        resNode = binaryTree.postOrderSearch(3);
        if (resNode != null) {
            System.out.println(resNode);
        }else {
            System.out.println("没找到该节点");
        }

    }
}

class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void proOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    /**
     * 树的前序
     *
     * @param no 查找编号
     * @return 结果
     */
    public TreeNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 树的中序
     *
     * @param no 查找编号
     * @return 结果
     */
    public TreeNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 树的后续
     *
     * @param no 查找编号
     * @return 结果
     */
    public TreeNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class TreeNode {
    private int no;
    private String name;
    private TreeNode leftNode;
    private TreeNode rightNode;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序查找
     *
     * @param no 查找编号
     * @return 返回找到的节点，没找到返回null
     */
    public TreeNode preOrderSearch(int no) {
        TreeNode resultNode = null;
        //前序，先比较当前根节点
        if (this.no == no) {
            return this;
        }
        //如果左节点不为空，则递归前序遍历
        if (this.leftNode != null) {
            resultNode = this.leftNode.preOrderSearch(no);
            //如果递归找到了，直接返回结果
            if (resultNode != null) {
                return resultNode;
            }
        }
        if (this.rightNode != null) {
            resultNode = this.rightNode.preOrderSearch(no);
        }
        //不管右递归找没找到都返回
        return resultNode;
    }

    /**
     * 中序查找
     *
     * @param no 查找节点编号
     * @return 返回找到的节点
     */
    public TreeNode infixOrderSearch(int no) {
        TreeNode resultNode = null;
        if (this.leftNode != null) {
            resultNode = this.leftNode.infixOrderSearch(no);
            if (resultNode != null) {
                return resultNode;
            }
        }
        if (this.no == no) {
            return this;
        }
        if (this.rightNode != null) {
            resultNode = this.rightNode.infixOrderSearch(no);
        }
        return resultNode;
    }

    /**
     * 后序查找
     *
     * @param no 查找节点编号
     * @return 返回找到的节点
     */
    public TreeNode postOrderSearch(int no) {
        TreeNode resultNode = null;
        if (this.leftNode != null) {
            resultNode = this.leftNode.postOrderSearch(no);
            if (resultNode != null) {
                return resultNode;
            }
        }
        if (this.rightNode != null) {
            resultNode = this.rightNode.postOrderSearch(no);
            if (resultNode != null) {
                return resultNode;
            }
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }

    public TreeNode() {
    }

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public TreeNode(int no, String name, TreeNode leftNode, TreeNode rightNode) {
        this.no = no;
        this.name = name;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}