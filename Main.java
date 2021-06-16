package com.KPFU;
import javafx.scene.chart.ScatterChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Scanner;

public class Main {

//public static String filepath = "1.csv";

    public static void main(String[] args) {
	// write your code h
        System.out.println("hw");
        String path = "C:\\Users\\Ender\\IdeaProjects\\KNN7\\src\\com\\KPFU\\2.txt";
        //String path = "./1.txt";
        List<List<String>> trainTable = readTable(path);

        //System.out.println(trainTable.get(0).get(0));
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
        try (Scanner scanner = new Scanner(new File(path)))
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
        }


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
