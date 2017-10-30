package threeSum;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
       List<List<Integer>> result= new ArrayList<>();
        List<List<Integer>> result1= new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int m=j+1;m<nums.length;m++){
                    if(nums[i]+nums[j]+nums[m]==0){
                        List<Integer> temp=new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[m]);
                        result.add(temp);
                    }
                }
            }
        }
        int n=result.size();

        for (int p=n-1;p>=1;p--){
            for(int q=p-1;q>=0;q--)
                if (isEqual(result.get(p), result.get(q))) {
                    result.remove(result.get(p));
                    break;
                }
            }




        return result;
    }
    public boolean isEqual(List<Integer> list1,List<Integer> list2){
        int i=0;
        int  n=list1.size();
        Collections.sort(list1);
        Collections.sort(list2);
        for(;i<n;i++){
            if(list1.get(i)!=list2.get(i)){
                return false;
            }
        }
        return true;
    }
}
