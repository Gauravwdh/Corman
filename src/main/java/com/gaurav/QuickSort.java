package com.gaurav;

/**
 * Created by gauravwadhwa on 24/12/15.
 */
public class QuickSort {


    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int part = part(arr, start, end);
        quickSort(arr, start, part - 1);
        quickSort(arr, part + 1, end);
    }


    private static int part(int[] arr, int start, int end) {
        int sortedValue = arr[end];
        int partIndex = start;
        for (int i = start; i < end; i++) {
            if(arr[i]<sortedValue){
                swipe(arr, i, partIndex);
                partIndex++;
            }
        }
        swipe(arr, partIndex, end);
        return partIndex;
    }

    private static void swipe(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
