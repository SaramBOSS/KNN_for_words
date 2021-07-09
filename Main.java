package com.KPFU;
import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;
import java.util.Scanner;

public class Main {

//public static String filepath = "1.csv";

    public static void main(String[] args) {
	// write your code h
        System.out.println("hw");
        String path = "C:\\Users\\Ender\\IdeaProjects\\KNN7\\src\\com\\KPFU\\1.txt";
        //String path = "./1.txt";
        List<List<String>> trainTable = readTable(path);
        //System.out.println(trainTable.size());
        List<String> wordKit = Arrays.asList("к.wav", "п.wav", "т.wav", "ф.wav", "х.wav", "ц.wav");

        KNN detector = new KNN(5, wordKit);
        detector.train(trainTable);
        double[] wordCeps = {-4, 1, 1, 1, 1,   1, 1, 1, 1, 1,   1, 1, 1, 1};
        detector.detect(wordCeps);

        /*float[] data = new float[trainTable.size()];
        float fs = 44100;

        System.out.println(trainTable.get(0).size());
        for (int i = 0; i < trainTable.size(); i++)
        {
            data[i] = Float.parseFloat(trainTable.get(i).get(0));
            //System.out.println(data[i]);
        }
        LoneWordPreprocessing wordPreprocessing = new LoneWordPreprocessing(data, fs);
        float[] dataNew = wordPreprocessing.deleteE();
        for (int i = 0; i < dataNew.length; i++)
            System.out.println(dataNew[i]);*/

        //for (int )
    }

    public static List<List<String>> readTable(String path)
    {
        //path = "1.csv";
        List<List<String>> table = new ArrayList<>();
        File fid = new File(path);
        System.out.println(fid);
        //Scanner scanne = new Scanner(fid);
        //new BufferedReader(new FileReader(path))
        //new File(path))

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251")))
        {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null)
            {
                table.add(getRecordFromLine(line));
            }
        }
        catch (IOException e)
        {
            System.out.println("err");
            //e.printStackTrace();
        }


        /*try (Scanner scanner = new Scanner(new File(path)))
        {
            System.out.println(scanner.hasNextLine());
            //System.out.println("!");
            while (scanner.hasNextLine()) {
                //System.out.println("!");
                String line = scanner.nextLine();
                //System.out.println(line);
                table.add(getRecordFromLine(line));
            }
        }
        catch (Exception ex)
        {
            System.out.println("err");
        }*/


        return table;
    }


    private static List<String> getRecordFromLine(String line)
    {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line))
        {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext())
            {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static double dist(double[] x_1, double[] x_2)
    {
        double d = 0;

        if (x_1.length == x_2.length)
            for (int i = 1; i < x_1.length; i++)
                d = d + Math.pow(x_1[i] - x_2[i], 2);
        return d;
    }
}
