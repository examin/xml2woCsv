package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class PerfectHillSeq {
    public static void main(String[] args) {
        int[] arr = {7,8,1,2,3,2,1,7,8};
        System.out.println(perfectHill(arr));
    }

    private static int perfectHill(int[] arr) {
        int[] dpInc =  new int[arr.length];
        int[] dpDec =  new int[arr.length];
        dpInc[0] = 1;
        dpDec[arr.length-1] = 1;
        for(int i = 1 ;i<arr.length;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[j+1]>arr[j]){
                    max++;
                }else break;
            }
            dpInc[i] =1+max;
        }
        for(int i = arr.length-1 ;i>-1;i--){
            int min = 0;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j-1]>arr[j]){
                    min++;
                }else break;
            }
            dpDec[i] =1+min;
        }
        int result = 0;
        for (int i = 0;i<arr.length;i++){
            result = Math.max((Math.min(dpDec[i],dpInc[i])*2)-1,result);
        }
        return result;
    }
}
