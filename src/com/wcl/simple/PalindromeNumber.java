/**
 * @Classname PalindromeNumber
 * @Description TODO
 * @Date 2022/3/23 23:24
 * @Created by 28327
 */

package com.wcl.simple;

/*
    给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

    回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    例如，121 是回文，而 123 不是。


 */
public class PalindromeNumber {
    public static void main(String[] args) {
        int x=123321;
        int j=56765;
        int y=124142;
        System.out.println(solution1(x));
        System.out.println(solution2(j));
        System.out.println(solution1(y));
    }

    /*
    运用数学的方法
     */
    public static boolean solution1(int x) {
        //判断x的边界
        // 0是回文数
        if (x == 0) {
            return true;
        }
        // -数和单位数肯定不是回文数
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        //定义中间变量,当x只要大于中间变量
        //例如121，第一次循环 m=0*10+121%10 =1
        //第二次循环 m=1*10 +12%10=12
        int midVariable = 0;
        while (x > midVariable) {
            midVariable = midVariable * 10 + x % 10;
            x /= 10;

        }
        //当x位数为偶数的时候就可以直接返回，如果为奇数，我们就应该对midVariable除以10，将奇数的中间位约区
        return x==midVariable || x==midVariable/10;

    }

    /*
    将数字转化为字符串，再将字符串反转比较
     */
    public static boolean solution2(int x) {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(x));
        return stringBuffer.toString().equals(stringBuffer.reverse().toString());
    }
}