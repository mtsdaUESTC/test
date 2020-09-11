import java.util.Stack;

public class BSTIterator {
    TreeNode cur;
    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        this.cur = root;
        while(cur !=null){
               stack.push(cur);
               cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if(!stack.isEmpty()){
            cur = stack.pop();
        }else{
            return 0;
        }
        int res = cur.val;
        if(cur.right!=null) {
            cur = cur.right;
            stack.push(cur);
        }
        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {

        return cur != null;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(7);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(9);
        TreeNode t5 = new TreeNode(20);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t4.right = t5;
        BSTIterator obj = new BSTIterator(t1);
        int param_1 = obj.next();
        int param_3 = obj.next();
        boolean param_2 = obj.hasNext();
        System.out.println(param_1);
        System.out.println(param_3);
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(obj.next());
        System.out.println(param_2);
    }
}
