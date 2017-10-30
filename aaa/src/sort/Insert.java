package sort;

public class Insert {
    public void insertSort(int [] nums){
        Util util=new Util();
        int n=nums.length;
        for(int i=1;i<n;i++){
            for (int j=i;j>0;j--){
                if(util.less(nums[j],nums[j-1])){
                    util.exch(nums,j,j-1);
                }
            }
        }
        for (int i=0;i<nums.length;i++){
            System.out.print(nums[i]);
        }
    }
}
