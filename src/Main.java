
import java.util.*;
import java.util.List;

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
    public boolean isValidBST(TreeNode root) {
        return isValidBstHelp(root,null,null);
    }//leetcode98 判断是否是二叉搜索树
    public boolean isValidBstHelp(TreeNode root,Integer lower,Integer upper){//leetcode98辅助
        if(root == null) return true;
        Integer val = root.val;
        if(lower!=null && lower >= val) return false;
        if(upper!=null && upper <= val) return false;
        if(!isValidBstHelp(root.right,val,upper)) return false;
        if(!isValidBstHelp(root.left,lower,val)) return false;
        return true;
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {  //leetcode100判断是否是同一个二叉树
        if(p == null && q == null) return true; //递归结束点
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
    //leetcode 106 从中序与后序遍历构造二叉树
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
        if(head == null) return null;
        ListNode mid = findMiddle(head);
        TreeNode node = new TreeNode(mid.val);
        if(head == mid) return node; //注意这里！是和数组不一样的地方。
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }
    public ListNode findMiddle(ListNode head){
        ListNode prePtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while(fastPtr!=null && fastPtr.next!=null){
            prePtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        if(prePtr != null){
            prePtr.next = null;
        }
        return slowPtr;
    }
    //leetcode 110 判断是否为高度平衡的平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }
    public int depth(TreeNode root){
        if(root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        if(left>=0 && right>=0 && Math.abs(left-right)<=1){
            return Math.max(left,right) + 1;
        }else{
            return -1; //每一层都会传递-1回去
        }
    }
    //leetcode 111 二叉树最小深度
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int left = 0;
        int right = 0;
        if(root.left!=null) left = minDepth(root.left)+1;
        if(root.right!=null) right = minDepth(root.right)+1;
        if(left == 0 || right ==0){
            return right == 0? left:right;
        }else{
            return Math.min(left,right);
        }
    }
    //leetcode 112 二叉树路径总和：判断是否有路径上的和等于目标值
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        sum = sum-root.val;
        if(root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
    }
    //leetcode 113 路径总和2：给出具体路径
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return res;
        int singleSum = 0;
        List<Integer> list = new ArrayList<>();
        pathHelp(root,sum,list,singleSum);
        return this.res;
    }
    public void pathHelp(TreeNode root,int sum,List<Integer> list,int singleSum){
        singleSum += root.val;
        list.add(root.val);
        if(root.left!=null){
            pathHelp(root.left,sum,list,singleSum);
            list.remove(list.size()-1);
        }
        if(root.right!=null){
            pathHelp(root.right,sum,list,singleSum);
            list.remove(list.size()-1);
        }
        if(root.left==null && root.right ==null){
            if(singleSum == sum){
                res.add(new ArrayList<>(list));
                return ;
            }
            return;
        }
    }
    //LeetCode114 二叉树展开为链表
    public void flatten(TreeNode root) {
        while(root != null){
            if(root.left == null){
                root = root.right;
            }else{
                TreeNode pre = root.left;
                while(pre.right!=null){
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }
    //leetcode116 填充每个节点的下一个右侧节点指针  有待优化
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Node> list = new ArrayList<>();
            while(count>0){
                Node node = queue.poll();
                list.add(node);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count --;
            }
            for (int i = 0;i<list.size()-1;i++){
                list.get(i).next  = list.get(i+1);
            }
        }
        return root;
    }
    //leetcode117 填充每个节点的下一个右侧指针节点2 （不是完全二叉树）
    public Node connect2(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Node> list = new ArrayList<>();
            while(count>0){
                Node node = queue.poll();
                list.add(node);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count --;
            }
            for (int i = 0;i<list.size()-1;i++){
                list.get(i).next  = list.get(i+1);
            }
        }
        return root;
    }
    //leetcode124 计算任意路径的最大路径和
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        maxGain(root);
        return max;
    }
    public int  maxGain(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = Math.max(maxGain(root.left),0);
        int right = Math.max(maxGain(root.right),0);
        int NewPath = root.val + left + right;
        max = Math.max(max,NewPath);
        return root.val+Math.max(left,right);
    }
    //leetcode129 求根到叶子节点数字之和
    int result = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        sumNumbersHelp(root,0);
        return this.result;
    }
    public void sumNumbersHelp(TreeNode root,int sum){
        sum = sum*10+root.val;
        if(root.left == null && root.right == null){
            result += sum;
            return;
        }
        int temp = sum;
        if(root.left!= null) sumNumbersHelp(root.left,sum);
        if(root.right!=null) sumNumbersHelp(root.right,temp);
    }
    //leetcode199 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue= new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while(count > 0){
                TreeNode node = queue.poll();
                if(count == 1) list.add(node.val);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count --;
            }
        }
        return list;
    }
    //leetcode222 完全二叉树的节点个数
    public int countNodes(TreeNode root) { //非递归解法
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return 0;
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while(count != 0){
                res  += 1;
                TreeNode node = queue.poll();
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                count--;
            }
        }
        return res;
    }
    public int countNodes2(TreeNode root) {//递归解法
        if(root == null) return 0;
        int left = 0;
        int right = 0;
        left = countNodes2(root.left)+1;
        right = countNodes2(root.right)+1;
        return left + right-1;
    }
    //226 翻转二叉树
    public TreeNode invertTree(TreeNode root) {//递归
        if(root == null) return null;
        if(root.left!=null) invertTree(root.left);
        if(root.right!=null) invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
    public TreeNode invertTree2(TreeNode root){//非递归
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode res = root;
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while(count>0){
                TreeNode node = queue.poll();
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
                TreeNode temp = node.left;
                node.left = root.right;
                root.right = temp;
                count --;
            }
        }
        return root;
    }
    //leetcode230 二叉搜索树中第k小的元素
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        if(root.left == null && root.right == null) {
            count++;
            return root.val;
        }
        int res = 0;
        if(root.left!=null){
            res = kthSmallest(root.left,k);
            if(count == k) return res;
        }
        count++;
        if(k == count) return root.val;
        if(root.right!=null){
            res = kthSmallest(root.right,k);
            if (count == k) return res;
        }
        return res;
    }
    //leetcode235 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;
        TreeNode left = null;
        TreeNode right = null;
        left = lowestCommonAncestor(root.left,p,q);
        right = lowestCommonAncestor(root.right,p,q);
        if((left == q && right == p) || (left == p && right == q)){
            return root;
        }
        return left == null ? right:left;
    }
    //leetcode257 二叉树的所有路径
    List<String> strRes = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return strRes;
        binaryTreePathsHelp(root,"");
        return strRes;
    }
    public void binaryTreePathsHelp(TreeNode root,String str){
        if(root!=null){
            str = str + root.val;
        }
        if(root.left == null && root.right == null){
            strRes.add(str);
            return;
        }else{
            str = str+"->";
        }
        if(root.left!=null) binaryTreePathsHelp(root.left,str);
        if(root.right!=null) binaryTreePathsHelp(root.right,str);
    }
    //leetcode404 左叶子之和
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        if(root.left!=null && root.left.left == null && root.left.right == null ){
            sum = sum + root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum;
    }
    //leetcode 429 N叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while(count>0){
                Node node = queue.poll();
                list.add(node.val);
                for(Node tempNode:node.children){
                    queue.add(tempNode);
                }
                count --;
            }
            res.add(list);
        }
        return res;
    }
    //leetcode437 路径总和3 (两层递归，解决树的子结构问题)
    public int pathSum3(TreeNode root, int sum) {
        if(root == null) return 0;
        return pathSumHelp(root,sum)+pathSum3(root.left,sum)+pathSum3(root.right,sum);
    }
    public int pathSumHelp(TreeNode root,int sum){
        if(root == null) return 0;
        int temp = 0;
        sum = sum-root.val;
        if(sum == 0){
            temp++;
        }
        return temp+pathSumHelp(root.left,sum)+pathSumHelp(root.right,sum);
    }
    //leetcode 450 删除二叉搜索树中的节点
    public int successor(TreeNode root){
         root = root.right;
         while(root.left!=null) {
             root = root.left;
         }
         return root.val;
    }
    public int predecessor(TreeNode root){
        root = root.left;
        while(root.right!=null) root = root.right;
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root,int key){
        if(root == null) return null;
        if(key>root.val) root.right = deleteNode(root.right,key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) root = null;
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
    //leetcode 501 二叉搜索树中的众数  未解决
    int [] findModeRes;
    public int[] findMode(TreeNode root) {
        if(root == null) return findModeRes;
        findModeHelp(root);
        return findModeRes;
    }
    public void findModeHelp(TreeNode root){
        if(root == null) return;
        findModeHelp(root.left);

    }
    //leetcode 508 出现次数最多的子树元素和
//    int [] findFrequentRes;
//    public int[] findFrequentTreeSum(TreeNode root) {
//        int []res = new int[]{0,2};
//
//    }
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(-1);
        TreeNode t2 = new TreeNode(0);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(-2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(8);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t6;

//        System.out.println(levelOrder(t1));
//        System.out.println(inorderTraversal(t1));
//        System.out.println(preorderTraversal(t1));
//        System.out.println(postorderTraversal(t1));
//        System.out.println(isSymmetric(t1));
//        System.out.println(maxDepth(t1));
//        System.out.println("sss");
//        System.out.println(zigzagLevelOrder(t1));
        Main main1 = new Main();
//        System.out.println(main1.maxPathSum(t1));
        main1.lowestCommonAncestor(t1,t6,t5);
    }

}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
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
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
