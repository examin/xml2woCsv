package micro.examin.xml2woCsv.Try;

public class OtherProduct {
    public static void main(String[] args) {
        int arr[] = {1,4,5,0,9,9};
        int n = arr.length;
        int revProdArr[] = new int[n];
        revProdArr[n-1]= arr[n-1];
        for(int i=n-1;i>0;i++){
            revProdArr[i]= arr[i];
        }
        int prev = 0;
        for(int i =0;i<n;i++){
            arr[i] = prev==0?1*revProdArr[i+1]:prev*revProdArr[i+1];
            prev *= arr[i];
        }
    }
}
