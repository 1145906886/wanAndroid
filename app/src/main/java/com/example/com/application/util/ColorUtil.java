package com.example.com.application.util;

import android.graphics.Color;
import java.util.Random;

/**
 *  随机颜色 工具类
 */

public class ColorUtil {

    public static int getRandomColor(){

        Random random = new Random();

        int red = random.nextInt(160);
        int green = random.nextInt(160);
        int blue = random.nextInt(160);

        return Color.rgb(red,green,blue);

    }

}
