package FindMedianProblem.medianOfMeadian;

import FindMedianProblem.randomizedQuickSort.RandomizedQuickSort;

import java.util.Arrays;

public class medianOfMedian {

    public int select(int arr[], int i, int n, int p, int q, boolean first_enter){
        //System.out.println("i now is " + i);
        if (first_enter){
            if (n%2== 0){
                i--;
            }
        }

        if (p==q) return arr[p];

        //System.out.println("n is " + n);
        int med_arr[] = get_medians(arr, n);
        /*for(int c = 0; c<med_arr.length; c++){
            System.out.print(med_arr[c] + " ");
        }
        System.out.println();*/

        int x;
        if (med_arr.length > 5) {
            x = select(med_arr, (med_arr.length) / 2, med_arr.length, 0, med_arr.length - 1, true);
        }else if (med_arr.length == 0){
            x = arr[0];
        }else {
            x= med_arr[0];
        }

        int r = med_partition(arr, x, p, q);

        int k=r-p;

        /*System.out.println("k is " + k);
        for(int c = p; c<q+1; c++){
            System.out.print(arr[c] + " ");
        }
        System.out.println();
        System.out.println("================================");*/

        if (i==k) return arr[r];
        else if (i < k) return select(arr, i, (r-p), p, r-1, false);
        else return select(arr, i-k-1, (q-r), r+1, q, false);

        //int k = r-p;
        /*if (i==k) return arr[r];
        if (i<k) return select(arr, i, n * (3/4),  p, r-1, false);
        else return select(arr, i-k-1, n * (3/4),r+1, q , false);*/
    }

    private int med_partition(int[] arr, int x, int p, int q) {
        int ind = get_ind_x(arr, x, p, q);
        swap(arr,0, ind);
        return partition(arr,p , q);
    }

    private int get_ind_x(int[] arr, int x, int p, int q) {
        for (int i=p; i <q; i++){
            if (arr[i] == x) return i;
        }
        return 0;
    }

    private int[] get_medians(int[] arr, int n) {
        int[] med_arr = new int[n/5];
        int c=0;
        for (int i =0; i<n && n-i > 4;) {
            //System.out.println("itr number is " + i);
            int temp[] = {0,0,0,0,0};
            System.arraycopy(arr, i, temp, 0, 5);
            Arrays.sort(temp);
            med_arr[c] = temp[2];
            c++;
            i+=5;
        }
        return med_arr;
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
        int arr[] = {21753, 18079, 71794, -96073, 58485, 34634, -66920, -59759, 91003, -21203, 49281, 99944, 70933, -91521, -35218};
        medianOfMedian med = new medianOfMedian();
        int x=1;
        //rand.test(arr, x);
        //System.out.println(x);
        //System.out.println(arr[2]);
        System.out.println("index to search for is " + ((arr.length/2)));
        System.out.println(med.select(arr,((arr.length)/2), arr.length,  0, arr.length-1, true));
    }

}
//{-96073, -91521, -66920, -59759, -35218, -21203, 18079, 21753, 34634, 49281, 58485, 70933, 71794, 91003, 99944}
//right answer is :- 21753
