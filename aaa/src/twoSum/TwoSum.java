package twoSum;

public class TwoSum {
    public  int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int []indexs={0,0};
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if(nums[i]+nums[j]==target){
                    indexs[0]=i;
                    indexs[1]=j;
                    break;
                }
            }
        }
        return indexs;
    }
}
