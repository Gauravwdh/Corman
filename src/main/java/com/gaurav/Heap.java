package com.gaurav;

/**
 * Created by gauravwadhwa on 23/12/15.
 */
public class Heap {


    public static void printHeap(int arr[], int heapSize) {
        if (heapSize == 0) {
            return;
        }
        int h = getHeapHeight(heapSize);
        int j = 0;
        for (int i = 1; i <= h; i++) {
            int n = maxNodesForH(i) - minNodesForH(i) + 1;
            while (j < heapSize && n > 0) {
                System.out.print(arr[j] + "  ");
                j++;
                n--;
            }
            System.out.println();
        }
    }


    public static int getHeapHeight(int heapNodeCount) {
        return (int) (1 + (Math.log(heapNodeCount) / Math.log(2)));
    }

    public static int minNodesForH(int h) {
        return maxNodesForH(h - 1) + 1;
    }

    public static int maxNodesForH(int h) {
        return (int) (Math.pow(2, h) - 1);
    }


    public static void maxHeapify(int[] arr, int index, int heapSize) {
        int leftIndex = leftIndex(index);
        int rightIndex = rightIndex(index);
        int largestIndex = index;
        if (leftIndex < heapSize && arr[leftIndex] > arr[largestIndex]) {
            largestIndex = leftIndex;
        }
        if (rightIndex < heapSize && arr[rightIndex] > arr[largestIndex]) {
            largestIndex = rightIndex;
        }
        if (largestIndex != index) {
            swip(arr, index, largestIndex);
            maxHeapify(arr, largestIndex, heapSize);
        }
    }


    public static void buildHeap(int[] arr, int heapSize) {
        int half = heapSize / 2;
        for (int i = half; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }
    }


    public static void heapSort(int[] arr, int heapSize) {
        buildHeap(arr, heapSize);
        int tempHeapSize = heapSize - 1;
        for (int i = 0; i < heapSize; i++) {
            maxHeapify(arr, 0, tempHeapSize + 1);
            swip(arr, 0, tempHeapSize);
            tempHeapSize--;
        }
    }


    public static void insert(int[] arr, int key, int heapSize) {
        arr[heapSize] = key;
        int i = heapSize;
        while (parentIndex(i) >= 0) {
            if(arr[parentIndex(i)] < arr[i]){
                swip(arr, parentIndex(i), i);
                i = parentIndex(i);
            }else{
                break;
            }
        }
    }


    private static void swip(int arr[], int current, int place) {
        int temp = arr[current];
        arr[current] = arr[place];
        arr[place] = temp;
    }


    public static int leftIndex(int index) {
        index += 1;
        return 2 * index - 1;
    }

    public static int rightIndex(int index) {
        index += 1;
        return 2 * index;
    }

    public static int parentIndex(int index) {
        return ((index + 1) / 2) - 1;
    }

    public static boolean isValidHeap(int[] arr, int heapSize){
        int n = parentIndex(heapSize-1);
        for (int i = 0; i < n; i++) {
            if((arr[i] < arr[leftIndex(i)]) && (arr[i]) < arr[rightIndex(i)]){
                return false;
            }
        }
        return true;
    }


}
