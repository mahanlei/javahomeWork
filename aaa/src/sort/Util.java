package sort;

public class Util {

    public boolean less(int a,int b){
        if(a<b) return true;
        return false;
    }
    public void exch(int[]nums,int a,int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
    public boolean isSort(int []nums){
        for (int i=0;i<nums.length;i++){
            if(nums[i]>nums[i+1]) return false;
        }
        return true;
    }
}
