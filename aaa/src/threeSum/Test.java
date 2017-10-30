package threeSum;

import java.util.List;

public class Test {
    public static void main (String[] args) {
        int[] sums = {-1,0,1,2,-1,-4};
        int [] sums2={0,0,0,0};
        ThreeSum three = new ThreeSum();
        List list1=three.threeSum(sums);
        for (int i=0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
        List list2=three.threeSum(sums2);
        for (int i=0;i<list2.size();i++){
            System.out.println(list2.get(i));
        }

    }
}
