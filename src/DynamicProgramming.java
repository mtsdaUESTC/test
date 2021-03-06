import java.util.Arrays;
import java.util.HashMap;

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

  /**
   * leetcode 354 俄罗斯套娃信封问题
   *给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
   *
   * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
   *
   * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
   *
   * 注意：不允许旋转信封。
   *
   *  
   * 示例 1：
   *
   * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
   * 输出：3
   * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
   * 示例 2：
   *
   * 输入：envelopes = [[1,1],[1,1],[1,1]]
   * 输出：1
   * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
   * @param envelopes
   * @return
   */
  public int maxEnvelopes(int[][] envelopes) {
    int n = envelopes.length;
    Arrays.sort(envelopes,((o1, o2) -> {
      return o1[0] == o2[0] ? o2[1]-o1[1] : o1[0]-o2[0];
    }));
    int [] height = new int[n];
    for (int i = 0;i < n; i++) {
      height[i] = envelopes [i][1];
    }
    return lengthOfLIS(height);
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
  HashMap<String,Integer> map2 = new HashMap<>();

  public int minDistance(String word1, String word2) {
     char []w1 = word1.toCharArray();
     char []w2 = word2.toCharArray();
    return minDistance(w1,w2,w1.length-1,w2.length-1);
  }

  /**
   * 备忘录+递归的形式处理子问题
   * @param word1
   * @param word2
   * @param i
   * @param j
   * @return
   */
  public int minDistance(char[] word1,char[] word2,int i, int j){
    if (map2.containsKey(i+","+j)){
      return map2.get(i+","+j);
    }
     if(i == -1) return j+1;
     if(j == -1) return i+1;
     if(word1[i] == word2[j]) {
       int res = minDistance(word1,word2,i-1,j-1);
       map2.put(i+","+j,res);
       return res;
     }else{
       int res = Math.min(minDistance(word1,word2,i-1,j)+1,
               Math.min(minDistance(word1,word2,i,j-1)+1,minDistance(word1,word2,i-1,j-1)+1));
       map2.put(i+","+j,res);
       return res;
     }
  }

  /**
   * 用完全动态规划的方式解决编辑距离问题
   * @param s1
   * @param s2
   * @return
   */
  public int minDistanceDp(String s1,String s2) {
    int m = s1.length(),n = s2.length();
    int [][]dp = new int[m+1][n+1];
    for(int i = 1;i <= m;i++) {
      dp[i][0] = i;
    }
    for(int j = 1;j <= n;j++) {
      dp[0][j] = j;
    }
    //自底向上求解
    for(int i = 1;i<=m;i++){
      for(int j = 1;j<=n;j++){
        if(s1.charAt(i-1) == s2.charAt(j-1)){
          dp[i][j] = dp[i-1][j-1];
        }else{
          dp[i][j] = min(dp[i-1][j]+1,dp[i][j-1]+1, dp[i-1][j-1]+1);
        }
      }
    }
    return dp[m][n];
  }

  public int min(int a,int b,int c){
    return Math.min(a,Math.min(b, c));
  }

  /** leetcode 887 鸡蛋掉落
   * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
   *
   * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
   *
   * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
   *
   * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
   *
   * 你的目标是确切地知道 F 的值是多少。
   *
   * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
   *
   * 示例 1：
   *
   * 输入：K = 1, N = 2
   * 输出：2
   * 解释：
   * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
   * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
   * 如果它没碎，那么我们肯定知道 F = 2 。
   * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
   * 示例 2：
   *
   * 输入：K = 2, N = 6
   * 输出：3
   * 示例 3：
   *
   * 输入：K = 3, N = 14
   * 输出：4
   */
  HashMap<String,Integer> eggMap = new HashMap<>();

  public int superEggDrop(int K, int N) {
    if (eggMap.containsKey(K+","+N)) return eggMap.get(K+","+N);
    if(K == 1) return N;
    if(N == 0) return 0;
    int res = Integer.MAX_VALUE;
    for(int i = 1;i<N+1;i++){
      res = Math.min(res,Math.max(superEggDrop(K-1,i-1),superEggDrop(K,N-i))+1);
      eggMap.put(K+","+N,res);
    }
    return res;
  }

  /**
   * leetcode 1143. 最长公共子序列
   * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
   *
   * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
   * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
   *
   * 若这两个字符串没有公共子序列，则返回 0。
   *
   * 示例 1:
   *
   * 输入：text1 = "abcde", text2 = "ace"
   * 输出：3
   * 解释：最长公共子序列是 "ace"，它的长度为 3。
   * 示例 2:
   *
   * 输入：text1 = "abc", text2 = "abc"
   * 输出：3
   * 解释：最长公共子序列是 "abc"，它的长度为 3。
   * 示例 3:
   *
   * 输入：text1 = "abc", text2 = "def"
   * 输出：0
   * 解释：两个字符串没有公共子序列，返回 0。
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int [][] dp = new int[m+1][n+1];
    for (int k = 0;k<m;k++) {
      dp[k][0] = 0;
    }
    for (int l = 0;l<n;l++) {
      dp[0][l] = 0;
    }
    for (int i = 1;i<= m;i++) {
      for (int j = 1;j<= n;j++) {
        if (text1.charAt(i-1) == text2.charAt(j-1)) {
          dp[i][j] = dp[i-1][j-1] + 1;
        }else {
          dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
        }
      }
    }
    return dp[m][n];
  }

  /**
   * leetcode 516 最长回文子序列
   * 516. 最长回文子序列
   * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
   *
   * 示例 1:
   * 输入:
   *
   * "bbbab"
   * 输出:
   *
   * 4
   * 一个可能的最长回文子序列为 "bbbb"。
   *
   * 示例 2:
   * 输入:
   *
   * "cbbd"
   * 输出:
   *
   * 2
   * 一个可能的最长回文子序列为 "bb"。
   *
   *
   * @param s
   * @return
   */
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j] = 0;
      }
    }
    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }
    for (int i = n - 2; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i + 1][j - 1] + 2;
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][n - 1];
  }

  /**
   * 1312. 让字符串成为回文串的最少插入次数
   * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
   *
   * 请你返回让 s 成为回文串的 最少操作次数 。
   *
   * 「回文串」是正读和反读都相同的字符串。
   *
   *
   *
   * 示例 1：
   *
   * 输入：s = "zzazz"
   * 输出：0
   * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
   * 示例 2：
   *
   * 输入：s = "mbadm"
   * 输出：2
   * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
   * 示例 3：
   *
   * 输入：s = "leetcode"
   * 输出：5
   * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
   * @param s
   * @return
   */
  public int minInsertions(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i = n - 2; i >= 0; i--) {
      for (int j = i + 1; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i + 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
        }
      }
    }
    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    DynamicProgramming dynamicProgramming = new DynamicProgramming();
    System.out.println(dynamicProgramming.superEggDrop(3,25));
  }

}
