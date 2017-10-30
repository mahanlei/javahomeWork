package twoSum;

import twoSum.TwoSum;

public class Main {

    public static void main(String[] args) {
       int []nums={1,2,3,4,5};
       int target=4;
       TwoSum solution1=new TwoSum();
       int []indexs=solution1.twoSum(nums,target);
      for(int i=0;i<indexs.length;i++){
          System.out.print(indexs[i]);
      }



    }


}
