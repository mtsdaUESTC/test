import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 广度优先遍历
 * @author zyp
 */
public class BFSAlgorithm {

  /**
   * 给定一个二叉树，找出其最小深度。
   *
   * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
   *
   * 说明：叶子节点是指没有子节点的节点。
   *
   * 输入：root = [3,9,20,null,null,15,7]
   * 输出：2
   *
   * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
   * @param root
   * @return
   */
  public int minDepth(TreeNode root) {
    int res = 0;
    if (root == null) {
      return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    res = res + 1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeNode node = queue.poll();
        if (node.left == null && node.right == null) {
          return res;
        }
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
        size--;
      }
      res++;
    }
    return res;
  }

  /**
   * leetcode 752
   * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
   *
   * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
   *
   * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
   *
   * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
   *
   * 链接：https://leetcode-cn.com/problems/open-the-lock
   * @param deadends
   * @param target
   * @return
   */
  public int openLock(String[] deadends, String target) {
    int step = 0;
    String init = "0000";
    Set<String> deads = new HashSet<>();
    Set<String> visited = new HashSet<>();
    for (String s : deadends) {
      deads.add(s);
    }
    Queue<String> queue = new LinkedList<>();
    queue.add(init);
    visited.add(init);
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        String poll = queue.poll();
        if (deads.contains(poll)) {
          size--;
          continue;
        }
        if (poll.equals(target)) return step;
        for (int j = 0; j < 4; j++) {
          String plusOne = plusOne(poll, j);
          if (!visited.contains(plusOne)) {
            queue.add(plusOne);
            visited.add(plusOne);
          }
          String minusOne = minusOne(poll, j);
          if (!visited.contains(minusOne)) {
            queue.add(minusOne);
            visited.add(minusOne);
          }
        }
        size--;
      }
      step++;
    }
    return -1;
  }

  /**
   * 双向BFS
   * @param deadends
   * @param target
   * @return
   */
  public int openLockTwo(String[] deadends, String target) {
    int step = 0;
    Set<String> deads = new HashSet<>();
    for (String s : deadends) {
      deads.add(s);
    }
    Set<String> q1 = new HashSet<>();
    Set<String> q2 = new HashSet<>();
    Set<String> visited = new HashSet<>();
    q1.add("0000");
    q2.add(target);
    while(!q1.isEmpty() && !q2.isEmpty()){
      Set<String> temp = new HashSet<>();
      for (String cur : q1){
        if (deads.contains(cur)) continue;
        if (q2.contains(cur)) return step;
        visited.add(cur);
        for (int j = 0;j< 4;j++){
          String up = plusOne(cur,j);
          if (!visited.contains(up)) {
            temp.add(up);
          }
          String down = minusOne(cur,j);
          if (!visited.contains(down)) {
            temp.add(down);
          }
        }
      }
      step++;
      q1 = q2;
      q2 = temp;
    }
    return -1;
  }

  public String plusOne(String s,int j){
    char[] chars = s.toCharArray();
    if(chars[j] == '9'){
      chars[j] = '0';
    }else{
      chars[j] += 1;
    }
    return new String(chars);
  }

  public String minusOne(String s,int j){
    char[] chars = s.toCharArray();
    if(chars[j] == '0'){
      chars[j] = '9';
    }else {
      chars[j] -= 1;
    }
    return new String(chars);
  }

  public static void main(String[] args) {
    BFSAlgorithm bfsAlgorithm = new BFSAlgorithm();
    String[] deads = {"0000"};

    System.out.println(bfsAlgorithm.openLock(deads, "8888"));
  }

}
