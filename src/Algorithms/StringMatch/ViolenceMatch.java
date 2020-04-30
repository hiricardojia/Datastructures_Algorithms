package Algorithms.StringMatch;

/**
 * @Title: 匹配字符串
 * @Description: 暴力匹配算法
 * @Author: Jia RenHao
 * @Create: 2020-04-27
 * @Version: V1.0
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String pat = "la";
        String txt = "please whisper lalala";
        System.out.println(violenceMatch(pat, txt));
    }

    public static int violenceMatch(String pat, String txt) {
        int i = 0;
        int j = 0;
        int txtLength = txt.length();
        int patLength = pat.length();
        while (i < txtLength && j < patLength) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == patLength) {
            return i - patLength;
        } else {
            return -1;
        }
    }
}
