package ClosestPairProblem.divideAndConquer;

import java.util.Arrays;
import java.util.Comparator;

public class DivideAndConquer {

    private double[][] FindClosetPairDis(int[][] xarr, int [][] yarr){
        if (xarr.length<=3) return get_smallest_delta(xarr);

        int sep_line = xarr.length/2;
        int[][] Larr = Arrays.copyOfRange(xarr, 0, sep_line);
        int[][] Rarr = Arrays.copyOfRange(xarr, sep_line, xarr.length);

        double deltaL[][] = FindClosetPairDis(Larr, yarr);
        double deltaR[][] = FindClosetPairDis(Rarr, yarr);

        double[][] deltaWithPoints = new double[3][2];
        double delta;
        if (deltaR[0][0] < deltaL[0][0]){
            delta = deltaR[0][0];
            deltaWithPoints = Arrays.copyOfRange(deltaR, 0, deltaR.length);
        }else {
            delta = deltaL[0][0];
            deltaWithPoints = Arrays.copyOfRange(deltaL, 0, deltaL.length);
        }
        //double delta = Math.min(deltaR[0][0], deltaL[0][0]);
        int [][][] fliteredArr_withLength = deletePointsFurtherThanDelta(yarr, delta, xarr[sep_line]);

        deltaWithPoints  = getMinDelta(fliteredArr_withLength[0], fliteredArr_withLength[1][0][0], deltaWithPoints);

        return deltaWithPoints;
    }

    private double[][] getMinDelta(int[][] fliteredArr, int l, double[][] deltaWithPoints) {
        /*double[][] deltaWithPoints = new double[3][2];
        deltaWithPoints[0][0] =  Math.sqrt(Math.pow((fliteredArr[0][0] - fliteredArr[1][0]), 2) + Math.pow((fliteredArr[0][1] - fliteredArr[1][1]), 2));
        deltaWithPoints[1][0] = fliteredArr[0][0];
        deltaWithPoints[1][1] = fliteredArr[0][1];
        deltaWithPoints[2][0] = fliteredArr[1][0];
        deltaWithPoints[2][1] = fliteredArr[1][1];*/
        for (int i=0; i< l-7; i++){
            for (int j=1; j<8; j++){
                double temp =  Math.sqrt(Math.pow((fliteredArr[i][0] - fliteredArr[i+j][0]), 2) + Math.pow((fliteredArr[i][1] - fliteredArr[i+j][1]), 2));
                if (temp<deltaWithPoints[0][0]){
                    deltaWithPoints[0][0] = temp;
                    deltaWithPoints[1][0] = fliteredArr[i][0];
                    deltaWithPoints[1][1] = fliteredArr[i][1];
                    deltaWithPoints[2][0] = fliteredArr[i+j][0];
                    deltaWithPoints[2][1] = fliteredArr[i+j][1];
                }
            }
        }
        return deltaWithPoints;
    }

    private int[][][] deletePointsFurtherThanDelta(int[][] yarr, double delta, int[] med_point) {
        int[][] filteredArray = new int[yarr.length][2];
        int c=0;
        for (int i=0; i< yarr.length; i++){
            double temp =  Math.sqrt(Math.pow((yarr[i][0] - med_point[0]), 2) + Math.pow((yarr[i][1] - med_point[1]), 2));
            if (temp>delta) continue;
            filteredArray[c][0] = yarr[i][0];
            filteredArray[c][1] = yarr[i][1];
            c++;
        }
        int[][][] return_array = {filteredArray, {{c}}};
        return return_array;
    }

    private double[][] get_smallest_delta(int[][] xarr) {
        double[][] deltaWithPoints = new double[3][2];
        deltaWithPoints[0][0] =  Math.sqrt(Math.pow((xarr[0][0] - xarr[1][0]), 2) + Math.pow((xarr[0][1] - xarr[1][1]), 2));
        deltaWithPoints[1][0] = xarr[0][0];
        deltaWithPoints[1][1] = xarr[0][1];
        deltaWithPoints[2][0] = xarr[1][0];
        deltaWithPoints[2][1] = xarr[1][1];
        for(int i=0; i<xarr.length; i++){
            for(int j=i; j< xarr.length; j++){
                if (j==i) continue;
                double temp = Math.sqrt(Math.pow((xarr[i][0] - xarr[j][0]), 2) + Math.pow((xarr[i][1] - xarr[j][1]), 2));
                if (temp<deltaWithPoints[0][0]){
                    deltaWithPoints[0][0] = temp;
                    deltaWithPoints[1][0] = xarr[i][0];
                    deltaWithPoints[1][1] = xarr[i][1];
                    deltaWithPoints[2][0] = xarr[j][0];
                    deltaWithPoints[2][1] = xarr[j][1];
                }
            }
        }
        return deltaWithPoints;
    }

    private int[][] sortPointBasedOnY(int[][] pointsArr) {
        Arrays.sort(pointsArr, Comparator.comparingDouble(arr -> arr[1]));
        return pointsArr;
    }

    private int[][] sortPointBasedOnX(int[][] pointsArr) {
        Arrays.sort(pointsArr, Comparator.comparingDouble(arr -> arr[0]));
        return pointsArr;
    }

    double FindClosetPair(int[][] pointsArr){
        int[][] xarr = sortPointBasedOnX(pointsArr);
        int[][] yarr = sortPointBasedOnY(pointsArr);
        double deltaWithPoints[][] =  FindClosetPairDis(xarr, yarr);
        double xDiff = Math.abs(deltaWithPoints[1][0] - deltaWithPoints[2][0]);
        double yDiff = Math.abs(deltaWithPoints[1][1] - deltaWithPoints[2][1]);
        return Math.max(xDiff, yDiff);
    }

    public static void main(String[] args) {
        int[][] points_array = {{1,1},{3,3}, {15,5}, {6,6}};
        DivideAndConquer findCloset = new DivideAndConquer();
        double maxSquareLength = findCloset.FindClosetPair(points_array);
        System.out.println(maxSquareLength);


        /*Arrays.sort(filteredArray, Comparator.comparingDouble(arr -> arr[1]));
        for (int i=0; i<2; i++){
            System.out.print(filteredArray[i][0] + " " + filteredArray[i][1]);
            System.out.println();
        }*/
    }
}
