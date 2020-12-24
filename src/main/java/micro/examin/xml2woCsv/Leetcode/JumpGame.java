package micro.examin.xml2woCsv.Leetcode;

public class JumpGame {
    public static void main(String[] args) {
        int[] arr =  {1,1,0,1};
        System.out.println(canReachEnd(arr,0));
        System.out.println(canReachEnd(arr));
    }

    private static boolean canReachEnd(int[] arr) {
        if(arr.length==0){
            return true;
        }
        int maxReach = arr[0];
        int currMaxReach = arr[0];
        int currIndex = 0;
        while (currIndex!=arr.length-1){
            if(currMaxReach == currIndex){
                maxReach = Math.max(maxReach,currIndex+arr[currIndex]);
                if(currMaxReach == maxReach){
                    break;
                }
                currMaxReach = maxReach;
            }
            maxReach = Math.max(maxReach,currIndex+arr[currIndex]);
            currIndex++;
        }
        if(currIndex==arr.length-1){
            return true;
        }else return false;
    }

    private static boolean canReachEnd(int[] arr,int index) {
        if(index == arr.length-1){
            return true;
        }
        int maxJump = index+arr[index];
        for(int i = index+1;i<=maxJump&&i<arr.length;i++){
            if(canReachEnd(arr,i)){
                return true;
            }
        }
        return false;
    }
}
