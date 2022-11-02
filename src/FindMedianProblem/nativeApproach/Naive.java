package FindMedianProblem.nativeApproach;

import FindMedianProblem.randomizedQuickSort.RandomizedQuickSort;

import java.util.Arrays;

public class Naive {

    public int naive(int[] arr){
        Arrays.sort(arr);
        /*System.out.println("NAIVE SORT ");
        System.out.println();
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print("}");*/
        int med;
        if (arr.length%2==0){
            med = (arr.length)/2-1;
        }else {
            med = (arr.length)/2;
        }
        return arr[med];
    }

    public static void main(String[] args){
        int arr[] = {-3, -67, 79, -70, 27, 70, 42, 82, -61, -57, -70, -41, 22, -1, 37, -90, 27, -60};
        Naive nav = new Naive();
        System.out.println(nav.naive(arr));
    }
}
