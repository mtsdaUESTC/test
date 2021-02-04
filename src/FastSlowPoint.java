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

}
