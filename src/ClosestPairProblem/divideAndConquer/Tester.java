package ClosestPairProblem.divideAndConquer;

import java.util.Random;

public class Tester {
    double[][] get_greatest_length(double pointsArray[][]){
        //double delta = Math.max(Math.abs((pointsArray[0][0] - pointsArray[1][0])) , Math.abs((pointsArray[0][1] - pointsArray[1][1])));
        double[][] deltaWithPoints = new double[3][2];
        deltaWithPoints[0][0] =  Math.sqrt(Math.pow((pointsArray[0][0] - pointsArray[1][0]), 2) + Math.pow((pointsArray[0][1] - pointsArray[1][1]), 2));
        deltaWithPoints[1][0] = pointsArray[0][0];
        deltaWithPoints[1][1] = pointsArray[0][1];
        deltaWithPoints[2][0] = pointsArray[1][0];
        deltaWithPoints[2][1] = pointsArray[1][1];
        for(int i=0; i<pointsArray.length; i++){
            for(int j=i; j<pointsArray.length; j++){
                if (j==i) continue;
                double temp = Math.max(Math.abs((pointsArray[i][0] - pointsArray[j][0])) , Math.abs((pointsArray[i][1] - pointsArray[j][1])));
                if (temp<deltaWithPoints[0][0]){
                    deltaWithPoints[0][0] = temp;
                    deltaWithPoints[1][0] = pointsArray[i][0];
                    deltaWithPoints[1][1] = pointsArray[i][1];
                    deltaWithPoints[2][0] = pointsArray[j][0];
                    deltaWithPoints[2][1] = pointsArray[j][1];
                }
            }
        }
        return deltaWithPoints;
    }

    void test(int num_of_tests){
        for (int j=0; j<num_of_tests; j++) {
            int num_of_points = new Random().nextInt(4, 100000);
            double[][] points_array = new double[num_of_points][2];
            for (int i = 0; i < num_of_points; i++) {
                points_array[i][0] = new Random().nextDouble(-1000000, 1000000);
                points_array[i][1] = new Random().nextDouble(-1000000, 1000000);
            }
            DivideAndConquer findCloset = new DivideAndConquer();
            Tester t = new Tester();
            double maxSquareLength = findCloset.FindClosetPair(points_array);
            double[][] maxSquareLength_withTester = t.get_greatest_length(points_array);
            /*System.out.print("{ ");
            for (int i = 0; i < num_of_points; i++) {
                System.out.print("{");
                System.out.print((int)(points_array[i][0]) + "," + (int)(points_array[i][1]));
                System.out.print("}, ");
            }
            System.out.print(" }");
            System.out.println();*/
            System.out.println("Divide and Conquer result : " + maxSquareLength + " Tester result : " + maxSquareLength_withTester[0][0]);// + "points : ");
            //System.out.print(maxSquareLength_withTester[1][0] + " " + maxSquareLength_withTester[1][1] + " , " + maxSquareLength_withTester[2][0] + " " + maxSquareLength_withTester[2][1]);
            //System.out.println("=====================================================================");
        }
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.test(10);
    }

}
