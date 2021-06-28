package com.KPFU;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;

public class LoneWordPreprocessing {

    private double[] mData;
    private double mFs;

    public LoneWordPreprocessing(double[] data, double fs)
    {
        mData = data;
        mFs = fs;
    }

    public double[] deleteE()
    {
        int winLen = 10;
        List<Double> energyGist = new ArrayList<>();
        int i = 0;
        for (i = 0; i < mData.length; i+=winLen);
        {
            double energy = 0;
            for (int j = i; j <= winLen; j++)
            {
                if (j <= mData.length)
                {
                    energy = energy + Math.pow(mData[i],2);
                }
            }
            energyGist.add(energy);
        }

        double maxEn = max(energyGist);
        double meanEn = mean(energyGist);

        int tillE = 0;
        for (i = 0; i < mData.length; i+= winLen)
        {
            double energy = 0;
            for (int j = i; j <= winLen; j++)
            {
                if (j <= mData.length)
                {
                    energy = energy + Math.pow(mData[i],2);
                }
            }

            if (energy > meanEn)
            {
                tillE = i;
                break;
            }
        }

        double[] dataNew = new double[tillE];
        for ( i = 0; i < tillE; i++)
            dataNew[i] = mData[i];
        return dataNew;
    }

    private double mean(List<Double> arr)
    {
        double sum = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            sum = sum + arr.get(i);
        }
        return sum/arr.size();
    }
}
