import java.util.*;

public class Main {
    public static List<List<Integer>> levelOrder(TreeNode root) {    //层次遍历
       List<List<Integer>> res = new ArrayList<>();
       if(root == null) return res;
       Queue<TreeNode> queue = new LinkedList<>();
       queue.add(root);
       while(!queue.isEmpty()){
           int count = queue.size();
           List<Integer> list = new ArrayList<>();
           while(count>0){
               TreeNode node = queue.poll();
               list.add(node.val);
               if(node.left!=null) queue.add(node.left);
               if(node.right!=null) queue.add(node.right);
               count--;
           }
           res.add(list);
       }
       return res;
    } //层次遍历
    public static List<Integer> inorderTraversal(TreeNode root){           //非递归中序遍历
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }  //中序遍历
    public static List<Integer> preorderTraversal(TreeNode root) { //前序遍历
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
        }
        return res;
    } //前序遍历
    public static List<Integer> postorderTraversal(TreeNode root) {  // 后续遍历
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(0,node.val);
            if(node.left!=null) stack.push(node.left);
            if(node.right!=null) stack.push(node.right);
        }
        return res;
    }  //后序遍历
    public static boolean isSameTree(TreeNode p, TreeNode q) {  //判断是否是同一个二叉树
        if(q == null && q == null) return true; //递归结束点
        if(p!=null && q!=null && p.val == q.val){ //往下递归的条件
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right); //递归点
        }else{
            return false;
        }
    } //  leetcode100 判断两颗是否是相同的树
    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left,root.right);
    }//  leetcode 101判断是否是镜像二叉树。
    public static boolean isSymmetric(TreeNode p,TreeNode q){
        if(p == null && q == null) return true;
        if(q!=null && p!=null && q.val == p.val){
            return isSymmetric(p.left,q.right) && isSymmetric(p.right,q.left);
        }else{
            return false;
        }
    }//  leetcode 101判断是否是镜像二叉树的主体判断。
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int left = 0;
        int right = 0;
        if(root.left!=null) left = maxDepth(root.left)+1;
        if(root.right!=null) right = maxDepth(root.right)+1;
        return Math.max(left,right);
    }// leetcode 104  求二叉树最大深度
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {// leetcode 103 锯齿形层次遍历
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int flag = 1;
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while(count>0){
                TreeNode node = queue.poll();
                if((flag&1) != 1) {
                    list.add(0,node.val);
                }else{
                    list.add(node.val);
                }
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count --;
            }
            res.add(list);
            flag--;
        }
        return res;
    } // leetcode 103 锯齿形层次遍历
    //leetcode 105 从前序和中序遍历重建二叉树
    int pre_idx = 0;
    int []preorder;
    int []inorder;
    HashMap<Integer,Integer> idx_map = new HashMap<>();
    public  TreeNode buildTree(int [] preorder,int[] inorder){
        this.preorder = preorder;
        this.inorder = inorder;
        int idx = 0;
        for(Integer val : inorder) idx_map.put(val,idx++);
        return helper(0,inorder.length);
    }
    public TreeNode helper(int in_left,int in_right){
        if( in_left == in_right) return null;
        TreeNode root = new TreeNode(preorder[pre_idx]);
        int index = idx_map.get(preorder[pre_idx]);
        pre_idx++;
        root.left = helper(in_left,index);
        root.right = helper(index+1,in_right);
        return root;
    }
    //
    //
    //
    //leetcode 106 从中序与后序遍历构造二叉树  没通过
    int []postorder;
    int post_indx = 0;
    HashMap<Integer,Integer> pidx_map = new HashMap<>();
    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.post_indx = postorder.length-1;
        int idx = 0;
        for(Integer val : inorder) pidx_map.put(val,idx++);
        return helperPost(0,inorder.length);
    }
    public TreeNode helperPost(int in_left,int in_right){
        if(in_left == in_right) return null;
        TreeNode root = new TreeNode(postorder[post_indx]);
        int index = pidx_map.get(postorder[post_indx]);
        post_indx--;
        root.right = helperPost(index+1,in_right);
        root.left = helper(in_left,index);
        return root;
    }
    public static List<List<Integer>> levelOrderBottom(TreeNode root) { //leetcode 107 层次遍历2
        List<List<Integer>>res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List list = new ArrayList();
            int count = queue.size();
            while(count > 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count--;
            }
            res.add(0,list);
        }
        return res;
    } //LeetCode 107 二叉树的层次遍历2
    //
    //
    //
    //leetcode 108 从有序数组建立平衡二叉树
    int nums[];//｛-10，-3，0，5，9｝
    public TreeNode sortedArrayToBST(int[] nums) {// LeetCode 108将有序数组转化为二叉搜索树
        this.nums = nums;
        return helperToBst(0,nums.length-1);
    }
    public  TreeNode helperToBst(int left, int right){
        if (left == right) return null;
        int index = (right+left)/2; //注意迭代的时候是（left+right）/2
        TreeNode node = new TreeNode(nums[index]);
        node.left = helperToBst(left,index);
        node.right = helperToBst(index+1,right);
        return node;
    }
    //LeetCode 109 从有序链表建立平衡二叉树
    public TreeNode sortedListToBST(ListNode head) {//leetcode 109 从有序链表建立平衡二叉树

    }
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(0);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        t2.left = t4;
        t2.right = t5;
        t1.left = t2;
        t1.right = t3;
//        System.out.println(levelOrder(t1));
//        System.out.println(inorderTraversal(t1));
//        System.out.println(preorderTraversal(t1));
//        System.out.println(postorderTraversal(t1));
//        System.out.println(isSymmetric(t1));
//        System.out.println(maxDepth(t1));
//        System.out.println("sss");
//        System.out.println(zigzagLevelOrder(t1));
    }

}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
