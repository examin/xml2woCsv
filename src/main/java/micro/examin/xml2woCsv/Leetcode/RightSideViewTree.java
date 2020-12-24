package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RightSideViewTree {

    public List<Integer> rightSideView(TreeNode root) {
        AtomicInteger maxDepth = new AtomicInteger(-1);
        LinkedList<Integer> result =  new LinkedList<>();
        RightSideViewTree obj = new RightSideViewTree();
        obj.rightSideView(result,root, maxDepth, -1);
        return result;
    }

    private void rightSideView(LinkedList<Integer> result, TreeNode root, AtomicInteger depth, int currLevel) {
        if (null != root) {
            if (currLevel > depth.intValue()) {
                depth.incrementAndGet();
               result.add(root.val);
            }
            rightSideView(result,root.right,depth,currLevel+1);
            rightSideView(result,root.left,depth,currLevel+1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2= new TreeNode(3);
        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(5);

        root.left = root1;
        root.right = root2;
        root1.left = root3;
        root.right = root4;
        RightSideViewTree obj = new RightSideViewTree();
        List<Integer> result = obj.rightSideView(root);
        for(int curr : result){
            System.out.println(curr);
        }
    }
}
