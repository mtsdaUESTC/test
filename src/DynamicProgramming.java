

import java.util.*;

/**
 * 动态规划
 * @author zyp
 */
public class DynamicProgramming {

  /**leetcode509 斐波那契数
   * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
   * F(0) = 0,   F(1) = 1
   * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
   * 给定 N，计算 F(N)。
   * @param n
   * @return
   */
  public int fib (int n){
    int dp[] = new int[n];
    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 1;
    for (int i = 3;i<n;i++){
      dp[i] = dp[i-1]+dp[i-2];
    }
    return dp[n-1];
  }

  /**leetcode 322 零钱兑换
   * 给定不同面额的硬币 coins 和一个总金额 amount。
   * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
   * 如果没有任何一种硬币组合能组成总金额，返回 -1。
   * 你可以认为每种硬币的数量是无限的。
   *
   * 示例 1：
   * 输入：coins = [1, 2, 5], amount = 11
   * 输出：3
   * 解释：11 = 5 + 5 + 1
   * @param coins
   * @param amount
   * @return
   */
  int []coins;
  HashMap<Integer,Integer> map = new HashMap<>();

  public int coinChange(int[] coins, int amount) {//递归加剪枝的做法
    this.coins = coins;
    return dp(amount);
  }

  public int dp(int amount){
   if(map.containsKey(amount)) return map.get(amount);
    if(amount == 0) return 0;
    if(amount < 0) return -1;
    int res = Integer.MAX_VALUE;
    for(int coin:coins){
      int subproblem = dp(amount-coin);
      if(subproblem == -1) continue;
      res = Math.min(res,1+subproblem);
    }
     res = res != Integer.MAX_VALUE?res:-1;
    map.put(amount,res);
    return res;
  }

  public int coinChangeDP(int[] coins, int amount){
    int[] dp = new int[amount+1];
    Arrays.fill(dp,amount+1);
    dp[0] = 0;
    for (int i = 0;i<dp.length;i++){
      for(int coin:coins){
        if(i-coin<0) continue;
        dp[i] = Math.min(dp[i],1+dp[i-coin]);
      }
    }
    return (dp[amount] == amount+1)? -1: dp[amount];
  }
  /**leetCode 300 最长上升子序列
   * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
   * 示例:
   *
   * 输入: [10,9,2,5,3,7,101,18]
   * 输出: 4
   * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
   * 说明:
   * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
   * 你算法的时间复杂度应该为 O(n2) 。
   * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
   * @param nums
   */
  public int lengthOfLIS(int[] nums){
    int[] dp = new int[nums.length];
    Arrays.fill(dp,1);
    for(int i = 0;i<nums.length;i++){
      for(int j = 0;j < i;j++){
        if(nums[i]>nums[j])
          dp[i] = Math.max(dp[i],dp[j]+1);
      }
    }
    int res = 0;
    for (int i = 0; i < dp.length; i++) {
      res = Math.max(res, dp[i]);
    }
    return res;
  }

  /**编辑距离
   * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
   *
   * 你可以对一个单词进行如下三种操作：
   *
   * 插入一个字符
   * 删除一个字符
   * 替换一个字符
   *
   * 示例 1：
   *
   * 输入：word1 = "horse", word2 = "ros"
   * 输出：3
   * 解释：
   * horse -> rorse (将 'h' 替换为 'r')
   * rorse -> rose (删除 'r')
   * rose -> ros (删除 'e')
   * 示例 2：
   *
   * 输入：word1 = "intention", word2 = "execution"
   * 输出：5
   * 解释：
   * intention -> inention (删除 't')
   * inention -> enention (将 'i' 替换为 'e')
   * enention -> exention (将 'n' 替换为 'x')
   * exention -> exection (将 'n' 替换为 'c')
   * exection -> execution (插入 'u')
   * @param word1
   * @param word2
   * @return
   */
  public int minDistance(String word1, String word2) {
     char []w1 = word1.toCharArray();
     char []w2 = word2.toCharArray();
    return minDistance(w1,w2,w1.length-1,w2.length-1);
  }
  public int minDistance(char[] word1,char[] word2,int i, int j){
     if(i == -1) return j+1;
     if(j == -1) return i+1;
     if(word1[i] == word2[j]) {
       return minDistance(word1,word2,i-1,j-1);
     }else{
       return Math.min(minDistance(word1,word2,i-1,j)+1,
               Math.min(minDistance(word1,word2,i,j-1)+1,minDistance(word1,word2,i-1,j-1)+1));
     }
  }
  public static void main(String[] args) {
    DynamicProgramming dynamicProgramming = new DynamicProgramming();
    String s1 = "intention";
    String s2 = "execution";
    System.out.println(dynamicProgramming.minDistance(s1,s2));
  }

}
