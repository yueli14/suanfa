/**
 * @Classname LengthOfLastWord
 * @Description TODO
 * @Date 2022/3/31 22:17
 * @Created by 28327
 */

package com.wcl.simple;

/*
给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
来源：力扣（LeetCode）
输入：s = "Hello World"
输出：5
解释：最后一个单词是“World”，长度为5。
输入：s = "   fly me   to   the moon  "
输出：4
解释：最后一个单词是“moon”，长度为4

 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        String s = "a ";
        System.out.println(solution1(s));

    }

    /*
    反转字符串
     */
    public static int solution1(String s) {
        //判断倒数是否有空格,记录索引，很巧妙的方法
        int index = s.length() - 1;
        while (Character.isSpaceChar(s.charAt(index))) {
            index--;
        }
        //记录单词的长度
        int len = 0;
        //当索引大于0和当前char不是空格符号的时候进循环
        while (index >= 0 && !Character.isSpaceChar(s.charAt(index))) {
            index--;
            len++;
        }
        return len;
    }


    public static int solution5(String s) {
        if (s.length() == 1) {
            return 1;
        }
        //反转字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = s.length() - 1; i >= 0; i--) {
            stringBuffer.append(s.charAt(i));
        }
        //去掉该字符串末尾的空格符
        int index = 0;
        while (Character.isSpaceChar(stringBuffer.charAt(index))) {
            index++;
        }
        int len = 0;
        // 对于字符串进行判断，当不为空格
        while (index < s.length() && !Character.isSpaceChar(stringBuffer.charAt(index))) {
            len++;
            index++;

        }

        return len;
    }
}