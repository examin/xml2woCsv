package micro.examin.xml2woCsv.Leetcode;

public class ValBST {
    public static void main(String[] args) {
        TreeNode root =  new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(7);
        root.right.right = new TreeNode(6);
        root.right.left =  new TreeNode(8);
        isValidBST(root);

    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
         else return false;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}