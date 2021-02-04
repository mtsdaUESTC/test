package JUC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 * @author zyp
 */
public class BackTraceProgramming {

  /**
   * leetcode46 全排列
   * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
   *
   * 示例:
   *
   * 输入: [1,2,3]
   * 输出:
   * [
   *   [1,2,3],
   *   [1,3,2],
   *   [2,1,3],
   *   [2,3,1],
   *   [3,1,2],
   *   [3,2,1]
   * ]
   * 链接：https://leetcode-cn.com/problems/permutations
   * @param nums
   * @return
   */
  List<List<Integer>> res = new LinkedList<>();
  public List<List<Integer>> permute(int[] nums) {
    LinkedList<Integer> track = new LinkedList<>();
    backTraceForPermute(nums, track);
    return res;
  }
  public void backTraceForPermute(int[] nums, LinkedList<Integer> track){
    if (track.size() == nums.length) {
      res.add(new LinkedList<>(track));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (track.contains(nums[i])) continue;
      track.add(nums[i]);
      backTraceForPermute(nums, track);
      track.removeLast();
    }
  }

  /**
   * leetcode51 n皇后问题
   * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
   *
   * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
   *
   * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
   *
   * 链接：https://leetcode-cn.com/problems/n-queens
   * @param n
   * @return
   */
//  List<List<String>> resForNQueens = new ArrayList<>();
//  public List<List<String>> solveNQueens(int n) {
//    List<String> board = new ArrayList<>();
//    String
//    for (int i = 0;i<n;i++){
//
//    }
//  }
//  Sorting is the process of rearranging a sequence of objects so as to put them in
//  some logical order. For example, your credit card bill presents transactions in
//  order by date—they were likely put into that order by a sorting algorithm. In the
//  early days of computing, the common wisdom was that up to 30 percent of all computing cycles was spent sorting. If that fraction is lower today, one likely reason is that
//  sorting algorithms are relatively efficient, not that sorting has diminished in relative
//  importance. Indeed, the ubiquity of computer usage has put us awash in data, and the
//  first step to organizing data is often to sort it. All computer systems have implementations of sorting algorithms, for use by the system and by users.
//  There are three practical reasons for you to study sorting algorithms, even though
//  you might just use a system sort:
//      ■ Analyzing sorting algorithms is a thorough introduction to the approach that we
//  use to compare algorithm performance throughout the book.
//      ■ Similar techniques are effective in addressing other problems.
//      ■ We often use sorting algorithms as a starting point to solve other problems.
//  More important than these practical reasons is that the algorithms are elegant, classic,
//  and effective.
//  Sorting plays a major role in commercial data processing and in modern scientific
//  computing. Applications abound in transaction processing, combinatorial optimization, astrophysics, molecular dynamics, linguistics, genomics, weather prediction, and
//  many other fields. Indeed, a sorting algorithm (quicksort, in Section 2.3) was named
//  as one of the top ten algorithms for science and engineering of the 20th century.
//  In this chapter, we consider several classical sorting methods and an efficient implementation of a fundamental data type known as the priority queue. We discuss the
//  theoretical basis for comparing sorting algorithms and conclude the chapter with a
//  survey of applications of sorting and priority queues
}
