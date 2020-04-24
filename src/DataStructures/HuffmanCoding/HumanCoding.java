package DataStructures.HuffmanCoding;

import java.io.*;
import java.util.*;

/**
 * @Title: 赫夫曼编码
 * @Description: 将 "i like like like java do you like a java"进行赫夫曼编码
 * @Author: Jia RenHao
 * @Create: 2020-04-21
 * @Version: V1.0
 */
public class HumanCoding {
    public static void main(String[] args) {
        String context = "i like like like java do you like a java";
        //测试压缩
        byte[] contextBytes = context.getBytes();
        byte[] bytes = HuffmanTree.HuffmanCompression(contextBytes);
        System.out.println(Arrays.toString(bytes));
        //测试解压
        byte[] unzip = HuffmanTree.unzip(bytes, HuffmanTree.huffmanCode);
        String s = new String(unzip);
        System.out.println(s);
    }
}

class HuffmanTree {

    /**
     * 文件压缩
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     */
    public static void fileZip(File srcFile, File dstFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            byte[] srcByte = new byte[fis.available()];
            fis.read(srcByte);
            byte[] zipBytes = HuffmanTree.HuffmanCompression(srcByte);
            fos = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(zipBytes);
            oos.writeObject(HuffmanTree.huffmanCode);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件解压
     *
     * @param zipFile 压缩源文件
     * @param dstFile 目标文件
     */
    public static void fileUnZip(File zipFile, File dstFile) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            byte[] huffmanCode = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();
            byte[] unzip = unzip(huffmanCode, huffmanCodeMap);
            fos = new FileOutputStream(dstFile);
            fos.write(unzip);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 封装整个赫夫曼压缩过程
     *
     * @param srcByte 原始字节数组
     * @return 返回压缩后的字节数组
     */
    public static byte[] HuffmanCompression(byte[] srcByte) {
        //利用字节数组生成树节点
        List<Node> nodes = getNodes(srcByte);
        //利用生成的树节点集合创建赫夫曼树,得到根节点
        Node root = createHuffmanTree(nodes);
        //根据生成的树产生每个数据对应的赫夫曼编码
        Map<Byte, String> huffmanCodeMap = getPathCode(root);
        //进行最后的压缩
        return zip(srcByte, huffmanCodeMap);
    }

    /**
     * 统计字节数组的数据和次数，将原始的字节数组生成树节点
     *
     * @param bytes 原始字节数组
     * @return 返回生成的树节点的集合
     */
    public static List<Node> getNodes(byte[] bytes) {
        //创建一个存放节点的list集合
        List<Node> nodes = new ArrayList<>();
        //创建一个HashMap，用来记录每个byte数据和出现的次数（权值）
        Map<Byte, Integer> map = new HashMap<>();
        //使用Stream代替下面的for循环
        for (byte data : bytes) {
            map.merge(data, 1, Integer::sum);
        }
        /*for (byte data : bytes) {
            Integer count = map.get(data);
            if (count == null) {
                map.put(data, 1);
            } else {
                map.put(data, count + 1);
            }
        }*/
        //lambda表达式代替下面的foreach
        map.forEach((k, v) -> nodes.add(new Node(k, v)));
        /*for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }*/
        return nodes;
    }

    /**
     * 创建赫夫曼树
     *
     * @param nodes 传入生成的树节点
     * @return 返回生成的赫夫曼树的根节点
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    //用于存放最后生成的哈夫曼编码
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //用来拼接生成的叶子结点的路径编码
    static private StringBuilder dataPathCode = new StringBuilder();

    /**
     * 重载获得路径编码，对外接口
     *
     * @param root 传入根节点
     * @return 返回生成的编码表
     */
    public static Map<Byte, String> getPathCode(Node root) {
        if (root == null) {
            System.out.println("树为空");
        } else {
            getPathCode(root.getLeft(), "0", dataPathCode);
            getPathCode(root.getRight(), "1", dataPathCode);
        }
        return huffmanCode;
    }

    /**
     * 生成赫夫曼编码
     *
     * @param node         当前节点
     * @param code         左0右1
     * @param dataPathCode 当前路径编码
     */
    private static void getPathCode(Node node, String code, StringBuilder dataPathCode) {
        StringBuilder stringBuilder = new StringBuilder(dataPathCode);
        stringBuilder.append(code);
        if (node != null) {
            if (node.getData() == null) {
                getPathCode(node.getLeft(), "0", stringBuilder);
                getPathCode(node.getRight(), "1", stringBuilder);
            } else {
                huffmanCode.put(node.getData(), stringBuilder.toString());
            }
        }
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param contextByte    原始的字节数组
     * @param huffmanCodeMap 赫夫曼编码表
     * @return 赫夫曼压缩后的字节数组
     */
    public static byte[] zip(byte[] contextByte, Map<Byte, String> huffmanCodeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        //将原始的字节数组根据生成的编码表进行拼接
        for (byte b : contextByte) {
            stringBuilder.append(huffmanCodeMap.get(b));
        }
        //拼接结束，生成101010001011111111......
        //根据每八位进行截取，放到一个新的字节数组中
        //len统计新数组的长度
        int len;
        //也可以一句话 len = (stringBuilder.length() + 7) % 8;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //放入新数组
        byte[] huffmanCodeBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            if (8 * i + 8 > stringBuilder.length()) {
                huffmanCodeBytes[i] = (byte) Integer.parseInt(stringBuilder.substring(8 * i), 2);
            } else {
                huffmanCodeBytes[i] = (byte) Integer.parseInt(stringBuilder.substring(8 * i, 8 * i + 8), 2);
            }
        }
        return huffmanCodeBytes;
    }

    /**
     * 解压
     *
     * @param huffmanCodeBytes 压缩好的字节数组
     * @param huffmanCodeMap   赫夫曼编码表
     * @return 返回原始的文本内容
     */
    public static byte[] unzip(byte[] huffmanCodeBytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        //进行字符串拼接
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            //记录最后是不是字节数组的最后一个
            boolean isLastFlag = (i == huffmanCodeBytes.length - 1);
            stringBuilder.append(byteToBitString(huffmanCodeBytes[i], !isLastFlag));
        }
        //把编码表反置
        Map<String, Byte> newHuffmanCodeMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            newHuffmanCodeMap.put(entry.getValue(), entry.getKey());
        }
        //开始匹配字符串得到list
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length() - 1; ) {
            int count = 1;//扫描器
            Byte b;
            while (true) {
                String key = stringBuilder.substring(i, i + count);
                b = newHuffmanCodeMap.get(key);
                if (b == null) {
                    //没找到
                    count++;
                } else {
                    //匹配成功
                    break;
                }
            }
            list.add(b);
            i += count;
        }
        //此时list集合中已经存放了解压完的字符串
        byte[] resBytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resBytes[i] = list.get(i);
        }
        return resBytes;
    }

    /**
     * 将byte转换成bit字符串进行拼接
     *
     * @param b    传入的byte
     * @param flag 是否是最后一个
     * @return 返回bit字符串
     */
    private static String byteToBitString(byte b, boolean flag) {
        int temp = b;//将b转成int
        if (flag) {
            //对于正数需要高位补0，用按位或
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//将原先的十进制byte转成二进制
        if (flag || temp < 0) {
            //截取最后八位
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     */
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.preOrder();
        }
    }
}

class Node implements Comparable<Node> {

    private Byte data;//字母字节
    private int weight;//权值
    private Node left;//左节点
    private Node right;//右节点

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node = {" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}