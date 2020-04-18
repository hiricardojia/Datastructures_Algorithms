package DataStructures.sparsearray;

import java.io.*;

/**
 * 将棋盘的二维数组转化成稀疏数组进行存储
 *
 * @author Jia RenHao
 * @create 2020-03-31
 */
public class sparseArray implements Serializable {
    public static void main(String[] args) {
        //创建一个原始二维数组 0表示没有棋子，1黑子，2蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组 转 稀疏数组
        //1.先遍历二维数组，获得非0的数据
        int sum = 0;
        for (int[] ints : chessArr1) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (ints[j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        //3.遍历二维数组，将非0值存入稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //4.输出稀疏数组
        System.out.println();
        System.out.println("得到稀疏数组为：");
        for (int[] ints : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }

        //将稀疏数组  恢复成 原始的二维数组
        /*
            1.读取稀疏数组的第一行数据，创建原始的二维数组
            2.读取稀疏数组的后几行的数据，赋值给原始的二维数组即可
         */
        //读取第一行数据，创建原始数组
        int row_sparse = sparseArr[0][0];
        int col_sparse = sparseArr[0][1];
        int[][] chessArr2 = new int[row_sparse][col_sparse];
        //从第二行开始读取后面数据，赋值给原始数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //附加：将稀疏矩阵存储到本地磁盘，用IO序列化流
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("d:/object.txt"));
            oos.writeObject(sparseArr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //从磁盘读取存储的稀疏矩阵
        ObjectInputStream ois = null;
        int[][] sparseArr2 = new int[0][0];
        try {
            ois = new ObjectInputStream(new FileInputStream("d:/object.txt"));
            sparseArr2 = (int[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //遍历得到的稀疏数组
        System.out.println();
        System.out.println("从磁盘得到的稀疏数组：");
        for (int i = 0; i < sparseArr2.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
    }
}
