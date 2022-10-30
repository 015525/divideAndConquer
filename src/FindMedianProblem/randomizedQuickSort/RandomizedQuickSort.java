package FindMedianProblem.randomizedQuickSort;

import java.util.Random;

public class RandomizedQuickSort {
    int [] pivot_test = {3,2};
    int c=0;
    /*
    void test(int arr[], int x){
        x=9;
        arr[2]=9;
    }*/
    public int randSelect(int arr[], int p, int q, int i, boolean first_enter){
        if (first_enter){
            if (q%2!=0){
                //System.out.println("in first enter");
                i--;
            }
        }
        //System.out.println("p is " + p + " q is " + q + " i is " + i);
        if (p==q){
            //System.out.println("p is "+p);
            return arr[p];
        }
        int r = randPartion(arr, p, q);
        //System.out.println("random generated pivot is " + r);
        int k = r-p;
        /*System.out.println("k is " + k);
        for(int c = p; c<q+1; c++){
            System.out.print(arr[c] + " ");
        }
        System.out.println();
        System.out.println("================================");*/
        if (i==k){
            //System.out.println("i is "+i);
            return arr[r];
        }
        if (i<k){
            return randSelect(arr, p, r-1, i, false);
        }
        else {
            return randSelect(arr, r+1, q, i-k-1, false);
        }
    }

    private int randPartion(int[] arr, int p, int q) {
        int r = new Random().nextInt(p, q);
        //int r = 1;
        //int r= this.pivot_test[c++];
        //System.out.println("random pivote is " + r);
        swap(arr, p, r);
        return partition(arr, p, q);
    }

    private int partition(int[] arr, int p, int q) {
        //System.out.println("Entered");
        int x = arr[p];
        int i = p;
        //System.out.println("i of partition is " + i);
        for (int j=i+1; j<q+1; j++){
            if (arr[j] < x){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i, p);
        return i;
    }

    private void swap(int[] arr, int p, int r) {
        int temp = arr[p];
        arr[p] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args){
        int arr[] = {-3, -67, 79, -70, 27, 70, 42, 82, -61, -57, -70, -41, 22, -1, 37, -90, 27, -60};
        RandomizedQuickSort rand = new RandomizedQuickSort();
        int x=1;
        //rand.test(arr, x);
        //System.out.println(x);
        //System.out.println(arr[2]);
        System.out.println("index to search for is " + ((arr.length/2)));
        System.out.println(rand.randSelect(arr, 0, arr.length-1, ((arr.length)/2), true));
    }
}
