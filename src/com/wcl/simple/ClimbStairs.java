/**
 * @Classname ClimbStairs
 * @Description 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * @Date 2022/4/23 10:55
 * @Created by 28327
 */

package com.wcl.simple;

public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(solution1(10));
    }

    /**
     * 动态规划
     * 这题难在为什么爬n阶等于爬n-1阶和n-2阶的和
     * f(x)=f(x−1)+f(x−2)
     *
     * @param n
     * @return
     */
    public static int solution1(int n) {
        //  f(x−1) f(x−2)
        int pre = 1, preOfPre = 1, cur = 1;
        for(int i = 2; i <= n; i++) {
            cur = pre + preOfPre;
            preOfPre = pre;
            pre = cur;
        }
        return cur;

    }
}