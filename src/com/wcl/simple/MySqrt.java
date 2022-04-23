/**
 * @Classname MySqrt
 * @Description 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
 * 输入：x = 4
 * 输出：2
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 2022/4/23 10:25
 * @Created by 28327
 */

package com.wcl.simple;

public class MySqrt {
    public static void main(String[] args) {
        System.out.println(solution2(10));
    }

    /**
     * 袖珍计算器算法，x^1/2=(e^lnx)^(1/2)=(e^(1/2))^lnx
     * Math.exp(0.5 * Math.log(x))
     *
     * @param x
     * @return
     */
    public static int solution1(int x) {
        if (x == 0) {
            return 0;
        }
        int y = (int) Math.exp(0.5 * Math.log(x));
        //因为进行了强转。会有精度损失，判断y和y+1谁是正确答案
        return (long) (y + 1) * (y + 1) <= x ? y + 1 : y;

    }

    /**
     * 二分查找
     * 由于 x 平方根的整数部分  是满足 k^2≤x 的最大 k 值
     *
     * @param x
     * @return
     */


    public static int solution2(int x) {

        int l = 0, r = x, ans = -1;
        while (l <= r) {
            //取中间值
            int mid = l + (r - l) / 2;
            //如果中间值的平方小于x
            if ((long) mid * mid <= x) {
                //结果值等于中间值
                ans = mid;
                //左边界加一
                l = mid + 1;
            } else {
                //右边界-1
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 牛顿法
     * https://leetcode-cn.com/problems/sqrtx/solution/x-de-ping-fang-gen-by-leetcode-solution/
     *
     * @param x
     * @return
     */
    public static int solution3(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;


    }
}