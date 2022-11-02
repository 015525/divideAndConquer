package ClosestPairProblem.divideAndConquer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class DivideAndConquer {

    private double FindClosetPairDis(double[][] xarr, double[][] yarr){
        if (xarr.length<=3) return get_smallest_delta(xarr);

        int sep_line = xarr.length/2;
        double[][] Larr = Arrays.copyOfRange(xarr, 0, sep_line);
        double[][] Rarr = Arrays.copyOfRange(xarr, sep_line, xarr.length);

        double deltaL = FindClosetPairDis(Larr, yarr);
        double deltaR = FindClosetPairDis(Rarr, yarr);

        double delta = Math.min(deltaR, deltaL);
        double [][][] fliteredArr_withLength = deletePointsFurtherThanDelta(yarr, delta, xarr[sep_line]);

        delta  = getMinDelta(fliteredArr_withLength[0], fliteredArr_withLength[1][0][0], delta);

        return delta;
    }

    private double getMinDelta(double[][] fliteredArr, double l, double delta) {

        double final_length = l-7;
        if (l-7<0) final_length = l;
        for (int i=0; i< final_length; i++){
            for (int j=1; j<Math.min(8, l); j++){
                double temp =  Math.max(Math.abs((fliteredArr[i][0] - fliteredArr[i+j][0])) , Math.abs((fliteredArr[i][1] - fliteredArr[i+j][1])));
                if (temp<delta) delta = temp;
            }
        }
        return delta;
    }

    private double[][][] deletePointsFurtherThanDelta(double[][] yarr, double delta, double[] med_point) {
        double[][] filteredArray = new double[yarr.length][2];
        int c=0;
        for (int i=0; i< yarr.length; i++){
            double temp =  Math.sqrt(Math.pow((yarr[i][0] - med_point[0]), 2) + Math.pow((yarr[i][1] - med_point[1]), 2));
            if (temp>delta) continue;
            filteredArray[c][0] = yarr[i][0];
            filteredArray[c][1] = yarr[i][1];
            c++;
        }
        double[][][] return_array = {filteredArray, {{c}}};
        return return_array;
    }

    private double get_smallest_delta(double[][] xarr) {
        double delta = Math.max(Math.abs((xarr[0][0] - xarr[1][0])) , Math.abs((xarr[0][1] - xarr[1][1])));

        for(int i=0; i<xarr.length; i++){
            for(int j=i; j< xarr.length; j++){
                if (j==i) continue;
                double temp = Math.max(Math.abs((xarr[i][0] - xarr[j][0])) , Math.abs((xarr[i][1] - xarr[j][1])));
                if (temp<delta) delta = temp;
            }
        }
        return delta;
    }

    private void sortPointBasedOnY(double[][] pointsArr) {
        Arrays.sort(pointsArr, Comparator.comparingDouble(arr -> arr[1]));
    }

    private void sortPointBasedOnX(double[][] pointsArr) {
        Arrays.sort(pointsArr, Comparator.comparingDouble(arr -> arr[0]));
    }

    double FindClosetPair(double[][] pointsArr){
        double[][] xarr = Arrays.copyOfRange(pointsArr, 0, pointsArr.length);
        double[][] yarr = Arrays.copyOfRange(pointsArr, 0, pointsArr.length);
        sortPointBasedOnX(xarr);
        sortPointBasedOnY(yarr);
        return  FindClosetPairDis(xarr, yarr);
    }

    public static void main(String[] args) throws IOException {
        InputOutput i = new InputOutput();
        double[][] points_array = i.get_input(args[0]);//{ {0,8980}, {0,2449}, {0,8526}, {0,5297}, {0,3138}, {0,8026}, {0,8837}, {0,8528}, {0,6652}, {0,6525}, {0,5912}, {0,3268}, {0,6365}, {0,4046}, {0,6796}, {0,8421}, {0,6324}, {0,3370}, {0,5651}, {0,5779}, {0,2706}, {0,7002}, {0,4673}};
        DivideAndConquer findCloset = new DivideAndConquer();
        //Tester t = new Tester();
        double maxSquareLength = findCloset.FindClosetPair(points_array);
        i.writeOutput(maxSquareLength, args[0]);
        //double[][] maxSquareLength_withTester = t.get_greatest_length(points_array);
        //System.out.println("Divide and Conquer result : " + maxSquareLength + " Tester result : " + maxSquareLength_withTester[0][0] + " points : ");
        //System.out.print(maxSquareLength_withTester[1][0] + " " + maxSquareLength_withTester[1][1] + " , " + maxSquareLength_withTester[2][0] + " " + maxSquareLength_withTester[2][1]);
    }
    /* wrong test
    { {3724,3818}, {1499,3345}, {8261,555}, {3791,2985}, {3039,5626}, {1521,6781}, {5123,1275}, {8441,201}, {1381,802}, {8991,8670}, {8127,230}, {4970,7929}, {8591,3998}, {2611,1913}, {4926,2596}, {8025,7992},  }
    Divide and Conquer result : 324.8123955808902 Tester result : 313.4483871478815

    { {0,8980}, {0,2449}, {0,8526}, {0,5297}, {0,3138}, {0,8026}, {0,8837}, {0,8528}, {0,6652}, {0,6525}, {0,5912}, {0,3268}, {0,6365}, {0,4046}, {0,6796}, {0,8421}, {0,6324}, {0,3370}, {0,5651}, {0,5779}, {0,2706}, {0,7002}, {0,4673},  }
    Divide and Conquer result : 41.78614278938494 Tester result : 2.3607292648011935
     */
}
