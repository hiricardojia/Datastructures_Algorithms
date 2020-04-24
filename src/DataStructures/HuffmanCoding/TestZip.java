package DataStructures.HuffmanCoding;

import java.io.*;

/**
 * @Title: 利用赫夫曼编码测试文件的压缩和解压
 * @Description:
 * @Author: Jia RenHao
 * @Create: 2020-04-24
 * @Version: V1.0
 */
public class TestZip {
    public static void main(String[] args) {
        HuffmanTree.fileZip(new File("d:/src.bmp"),new File("d:/det.zip"));
        HuffmanTree.fileUnZip(new File("d:/det.zip"),new File("d:/src2.bmp"));
    }
}
