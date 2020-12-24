package micro.examin.xml2woCsv.Leetcode;

public class InOrder2BinaryTree {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8,9};
        System.out.println(bstFromPreorder(arr));
    }
    public static TreeNode bstFromPreorder(int[] arr) {
        return bstFromPreorder(arr,0,arr.length-1);
    }

    private static TreeNode bstFromPreorder(int[] arr, int start, int end) {
        if(start>end){
            return null;
        }
        int mid = (start+(end-start)/2);
        TreeNode root = new TreeNode(arr[mid]);
        root.left =  bstFromPreorder(arr,start,mid-1);
        root.right = bstFromPreorder(arr,mid+1,end);
        return root;
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
