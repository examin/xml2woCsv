package micro.examin.xml2woCsv.Leetcode;

import java.util.HashSet;

public class subsetDivk {
	public static void main(String[] args) {
		int arr[] =  {1,2,3,4,5,10,6,7,8,9};
		int k = 5;
		subsetDivk obj = new subsetDivk();
		System.out.println(obj.canArrange(arr,k))   ;
	}
	public boolean canArrange(int[] arr, int k) {
		HashSet<Integer> set =  new HashSet<>();
		for(int curr : arr){
			set.add(curr);
		}
		for(int i=0;i<arr.length;i++){
			if(set.contains(arr[i]%k)){
				set.remove(arr[i]%k);
				continue;
			}else if(arr[i]%k==0){

			}
			else{
				return false;
			}
		}
		return true;
	}
}
