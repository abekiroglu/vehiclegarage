package com.abekiroglu.vehicle.api.utils;

import java.util.Random;

public class RandomUtils {

    public static String getRandomAlphabeticString(int lowerSize, int upperSize){
        Random random = new Random();
        if(upperSize > lowerSize){
            int size = random.nextInt(upperSize - lowerSize) + lowerSize;
            int leftLimit = 97;
            int rightLimit = 122;
            StringBuilder buffer = new StringBuilder(size);
            for (int i = 0; i < size; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            return buffer.toString();
        }else{
            return null;
        }
    }
}
