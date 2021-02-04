/**
 * 快慢指针
 * @author zyp
 */
public class FastSlowPoint {

  /**leetCode 141
   * 判断环形链表
   * @param head
   * @return
   */
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next!=null){
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) return true;
    }
    return false;
  }

  /**leetCode 142
   * 判断环形链表并返回入环节点
   * @param head
   * @return
   */
  public ListNode detectCycle(ListNode head) {
    ListNode fast = head, slow = head;
    while (true) {
      if (fast == null || fast.next == null) return null;
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) break;
    }
    fast = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return fast;
  }

  /**leetcode 167
   * 两数之和
   * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
   *
   * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
   *
   * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
   *
   * @param numbers
   * @param target
   * @return
   */
  public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length-1;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum == target) {
        return new int[] {left+1 ,right+1};
      }else if (sum < target) {
        left++;
      }else if (sum > target) {
        right--;
      }
    }
    return new int[]{-1,-1};
  }
}
