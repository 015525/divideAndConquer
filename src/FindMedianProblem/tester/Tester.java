package FindMedianProblem.tester;

import ClosestPairProblem.divideAndConquer.DivideAndConquer;
import FindMedianProblem.medianOfMeadian.medianOfMedian;
import FindMedianProblem.nativeApproach.Naive;
import FindMedianProblem.randomizedQuickSort.RandomizedQuickSort;

import java.util.OptionalLong;
import java.util.Random;
import java.util.Arrays;

public class Tester {
    void test(int num_of_tests){
        for (int j=0; j<num_of_tests; j++) {
            int num_of_points = (int) Math.pow(10, (j+1));
            int[] arr = new int[num_of_points];
            for (int i = 0; i < num_of_points; i++) {
                arr[i] = new Random().nextInt(-100000, 100000);
            }
            medianOfMedian med = new medianOfMedian();
            Naive nav = new Naive();
            RandomizedQuickSort rand = new RandomizedQuickSort();

            long startTime;
            int med_of_medOfMeds = -1;
            int med_of_rand = -1;
            int med_naive = -1;
            long[] timesArray =  new long[3];
            for (int k=0; k<3; k++) {
                startTime = System.currentTimeMillis();
                med_of_medOfMeds = med.select(arr, ((arr.length) / 2), arr.length, 0, arr.length - 1, true);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfMedOfMeds = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /3;


            for (int k=0; k<3; k++) {
                startTime = System.currentTimeMillis();
                med_of_rand = rand.randSelect(arr, 0, arr.length - 1, ((arr.length) / 2), true);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfRand = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /3;

            for (int k=0; k<3; k++) {
                startTime = System.currentTimeMillis();
                med_naive = nav.naive(arr);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfNaive = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /3;


            System.out.println("Median of Median result : " + med_of_medOfMeds + " Random QuickSort result : " + med_of_rand + " Naive result : " + med_naive);
            System.out.println("Median of Median Time : " + timeOfMedOfMeds + " Random QuickSort Time : " + timeOfRand + " Naive Time : " + timeOfNaive);// + "points : ");
            System.out.println("============================================================================");
        }
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.test(7);
    }
}
