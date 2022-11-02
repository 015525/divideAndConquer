package FindMedianProblem.tester;

import FindMedianProblem.medianOfMeadian.medianOfMedian;
import FindMedianProblem.nativeApproach.Naive;
import FindMedianProblem.randomizedQuickSort.RandomizedQuickSort;

import java.util.Random;
import java.util.Arrays;

public class Tester {
    long[][] test(int num_of_tests, int no_of_runs_per_iteration){
        long[][] time_of_algorithms = new long[3][num_of_tests];
        for (int j=0; j<num_of_tests; j++) {
            int num_of_points = (int) Math.pow(10, (j+1));
            int[] arr = new int[num_of_points];
            for (int i = 0; i < num_of_points; i++) {
                arr[i] = new Random().nextInt(-100000, 100000);
            }

            /*System.out.print("{");
            for (int i = 0; i < num_of_points; i++) {
                System.out.print(arr[i] + ", ");
            }
            System.out.print("}");*/

            medianOfMedian med = new medianOfMedian();
            Naive nav = new Naive();
            RandomizedQuickSort rand = new RandomizedQuickSort();

            long startTime;
            int med_of_medOfMeds = -1;
            int med_of_rand = -1;
            int med_naive = -1;
            long[] timesArray =  new long[no_of_runs_per_iteration];
            for (int k=0; k<no_of_runs_per_iteration; k++) {
                startTime = System.currentTimeMillis();
                med_of_medOfMeds = med.select(arr, ((arr.length) / 2), arr.length, 0, arr.length - 1, true);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfMedOfMeds = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /no_of_runs_per_iteration;
            time_of_algorithms[0][j] = timeOfMedOfMeds;

            for (int k=0; k<no_of_runs_per_iteration; k++) {
                startTime = System.currentTimeMillis();
                med_of_rand = rand.randSelect(arr, 0, arr.length - 1, ((arr.length) / 2), true);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfRand = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /no_of_runs_per_iteration;
            time_of_algorithms[1][j] = timeOfRand;

            for (int k=0; k<no_of_runs_per_iteration; k++) {
                startTime = System.currentTimeMillis();
                med_naive = nav.naive(arr);
                timesArray[k] = System.currentTimeMillis() - startTime;
            }
            long timeOfNaive = Arrays.stream(timesArray).parallel().reduce((a, b)->a+b).getAsLong() /no_of_runs_per_iteration;
            time_of_algorithms[2][j] = timeOfNaive;

            System.out.println();
            System.out.println("No of runs for each algorithm : "+timesArray.length);
            System.out.println("Input size : "+ (int) Math.pow(10, (j+1)));
            System.out.println("Median of Median result : " + med_of_medOfMeds + " Random QuickSort result : " + med_of_rand + " Naive result : " + med_naive);
            System.out.println("Median of Median Time : " + timeOfMedOfMeds + " Random QuickSort Time : " + timeOfRand + " Naive Time : " + timeOfNaive);// + "points : ");
            System.out.println("============================================================================");
        }
        return time_of_algorithms;
    }

    public static void main(String[] args) {
        int num_of_tests=7;
        int no_of_runs_per_iteration=10;
        int input_array[] = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
        /*
        0 0 0 0 4 80 5625
        0 0 0 0 0 4 37
        0 0 0 0 1 10 48
         */
        long time_of_algorithms[][] = new long[3][num_of_tests];

        Tester t = new Tester();
        time_of_algorithms = t.test(num_of_tests, no_of_runs_per_iteration);
        for(int i=0; i<time_of_algorithms.length; i++){
            System.out.println();
            for (int j=0; j<time_of_algorithms[0].length; j++){
                System.out.print(time_of_algorithms[i][j] + " ");
            }
        }
    }
}
