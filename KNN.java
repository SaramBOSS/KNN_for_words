package com.KPFU;

import java.util.*;

public class KNN
{
    private class WordCeps implements  Comparable
    {
        public double[] mWordCeps;
        public String mWordName;
        public double mDist;


        @Override
        public int compareTo(Object o) {
            WordCeps wCeps = (WordCeps)o;
            if (mDist < wCeps.mDist)
                return -1;
            else if (mDist > wCeps.mDist)
                return 1;

            return 0;
        }
    }

    //private ArrayList<WordCeps> mWordTable;
    //private Comparator<WordCeps> mWordComparator;
    private WordCeps[] mWordTable;
    //private List<String> mWordKit;
    private int mKNNdim;
    private Map<String, Integer> mWordsValues;
    private int mNwords;
    //private double[] mWordCeps;
    //private double[][] mWordCepsTable;
    //private List<String> mWords;

    public KNN(int KNNdim, List<String> wordKit)
    {
        mKNNdim = KNNdim;
        mNwords = wordKit.size();
        mWordsValues = new HashMap<String, Integer>();
        for (int i = 0; i < mNwords; i++)
        {
            mWordsValues.put(wordKit.get(i), 0);
        }
    }

    public void train(double[][] wordCepsTable, List<String> words)
    {
        mWordTable = new WordCeps[wordCepsTable.length];
        for (int i = 0; i < wordCepsTable.length; i++) {
            mWordTable[i].mWordCeps = wordCepsTable[i];
            mWordTable[i].mWordName = words.get(i);
        }
        //mWordCepsTable = wordCepsTable;
        //mWords = words;
        //mWordTable.sort();
    }

    public void train(List<List<String>> wordCepsTable)
    {
        mWordTable = new WordCeps[wordCepsTable.size()];
        System.out.println(mWordTable.length);
        for (int i = 0; i < mWordTable.length; i++)
        {
            //double[] a = new double[wordCepsTable.get(0).size() - 1];
            mWordTable[i] = new WordCeps();
            mWordTable[i].mWordCeps = new double[wordCepsTable.get(0).size() - 1];
            ///double[] b;
            //b = a;
            //System.out.println(mWordTable[0].getClass().getSimpleName());
            //mWordTable[i].mWordCeps = a;
            for (int j = 0; j < wordCepsTable.get(0).size() - 1; j++)
            {
                mWordTable[i].mWordCeps[j] = Double.parseDouble(wordCepsTable.get(i).get(j));
            }
            mWordTable[i].mWordName = wordCepsTable.get(i).get(wordCepsTable.get(0).size()-1);
        }
    }

    public void detect(double[] wordCeps)
    {
        //double[] dists = new double[mWordCepsTable.length];
        for (int j = 0; j <= mWordTable.length; j++ )
        {
            mWordTable[j].mDist = dist(wordCeps, mWordTable[j].mWordCeps);
        }
        Arrays.sort(mWordTable);

        int i = 0;
        int wordValue = 0;
        while ((i <= mWordTable.length) & (wordValue <= mKNNdim))
        {
            wordValue = mWordsValues.get(mWordTable[i].mWordName) + 1;
            mWordsValues.put(mWordTable[i].mWordName, wordValue);
            i++;
        }
    }

    private Map<String, Integer> getWordsValues()
    {
        return  mWordsValues;
    }

    private double dist(double[] x_1, double[] x_2)
    {
        double d = 0;

        if (x_1.length == x_2.length)
            for (int i = 1; i < x_1.length; i++)
                d = d + Math.pow(x_1[i] - x_2[i], 2);
        return d;
    }
}
