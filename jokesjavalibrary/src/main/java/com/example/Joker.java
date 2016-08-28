package com.example;

import java.util.Random;

/**
 * Created by Prithvi on 8/2/2016.
 */
public class Joker {
    private static Random rand = new Random();

    public static String getJoke(){
        String joke;
        switch(rand.nextInt(3)){
            case 0:
                joke = "This is a funny joke.";
                break;
            case 1:
                joke = "This is totally a funny joke.";
                break;
            default:
                joke = "This is a awesome joke.";
                break;
        }
        return joke;
    }
}
