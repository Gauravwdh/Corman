package com.gaurav;

/**
 * Created by gauravwadhwa on 24/12/15.
 */
public class Util {

    public static void print(int[] arr) {
        System.out.println();
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.print("]");
        System.out.println();
    }
}
