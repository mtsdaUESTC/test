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

}
