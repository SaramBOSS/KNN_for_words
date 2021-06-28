package com.KPFU;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;

public class LoneWordPreprocessing {

    private float[] mData;
    private float mFs;

    public LoneWordPreprocessing(float[] data, float fs)
    {
        mData = data;
        mFs = fs;
    }

    public float[] deleteE()
    {
        int winLen = 10;
        List<Float> energyGist = new ArrayList<>();
        int i;
        for (i = 0; i < mData.length; i+=winLen)
        {
            float energy = 0;
            for (int j = i; j < i + winLen; j++)
            {
                if (j < mData.length)
                {
                    float pow = (float)Math.pow(mData[i],2);
                    energy = energy + pow;
                }
            }
            energyGist.add(energy);
        }

        //float maxEn = max(energyGist);
        float meanEn = mean(energyGist);

        int tillE = 0;
        for (i = 0; i < mData.length; i+= winLen)
        {
            float energy = 0;
            for (int j = i; j < i + winLen; j++)
            {
                if (j < mData.length)
                {
                    energy = energy + (float)Math.pow(mData[i],2);
                }
            }

            if (energy > meanEn)
            {
                tillE = i;
                break;
            }
        }

        float[] dataNew = new float[tillE];
        for ( i = 0; i < tillE; i++)
            dataNew[i] = mData[i];
        return dataNew;
    }

    private float mean(List<Float> arr)
    {
        float sum = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            sum = sum + arr.get(i);
        }
        return sum/arr.size();
    }
}
