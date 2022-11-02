package ClosestPairProblem.divideAndConquer;

import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputOutput {
    public double[][] get_input(String arg) {
        //System.out.println(arg);
        double[][] listOfPoints = new double[0][];
        try{
            File file = new File(arg);
            //BufferedReader reader = new BufferedReader(new FileReader(file));
            Scanner reader = new Scanner(file);
            int num_of_points = reader.nextInt();
            listOfPoints = new double[num_of_points][2];
            for (int i=0; i<num_of_points; i++) {
                double x = reader.nextDouble();
                double y = reader.nextDouble();
                listOfPoints[i][0] = x;
                listOfPoints[i][1] = y;
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfPoints;
    }

    public void writeOutput(double maxSquareLength, String path) throws IOException {
        String[] s = new String[10000];
        File f = new File(path);
        int c=0;
        do{
            s[c] = f.getName();
            c++;
            f = f.getParentFile();
        }while(f.getParentFile() != null);


        //System.out.println(s[0]);
        String output = f.getPath().concat("\\");
        for (int i=c-1; i>0; i--){
            output = output.concat(s[i] + "\\");
            output = output.concat("\\");
        }
        output = output.concat("output.txt");



        //System.out.println(output);
        Path outputPath = Path.of(output);
        Files.writeString(outputPath, String.valueOf((int)maxSquareLength));
        String answer = Files.readString(outputPath);
        //System.out.println(answer);
    }
}
