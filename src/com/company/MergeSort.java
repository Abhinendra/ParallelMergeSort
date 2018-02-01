package com.company;

public class MergeSort {

    public int[] mergeSort(int[] array,int ThreadSize) {
        int low = 0;
        int high = array.length;
        System.out.println(" Array length"+high);
        parrallelMergeSort(array, low, high-1,ThreadSize);
        return array;
    }

    private void parrallelMergeSort(int[] array, int low, int high,int ThreadSize){
       if(ThreadSize <=1){
           mergeSort(array,low,high);
           return;
       }
       int mid=(low+high)/2;
       Thread thread1=new Thread(new Runnable() {
           @Override
           public void run() {
               parrallelMergeSort(array,low,mid,ThreadSize/2);
           }
       });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                parrallelMergeSort(array,mid+1,high,ThreadSize/2);
            }
        });
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(array,low,mid,high);

    }

    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private void merge(int[] array, int low, int mid, int high) {
        System.out.println("Low "+low+" mid "+mid+" high "+high);
        int[] leftArray = new int[mid - low + 1];
        int[] rightArray = new int[high - mid];
        for (int index = 0; index < mid - low + 1; index++) {
            leftArray[index] = array[index + low];
        }
        for (int index = 0; index < high - mid; index++) {
            rightArray[index] = array[index + mid + 1];
        }
        System.out.println(leftArray.toString()+" "+rightArray.toString());
        int arrayLowIndex = low;
        int leftLowIndex = low;
        int rightLowIndex = mid+1;
        while (leftLowIndex <= mid && rightLowIndex <= high) {
            if (leftArray[leftLowIndex-low] <= rightArray[rightLowIndex-mid-1]) {
                array[arrayLowIndex] = leftArray[leftLowIndex-low];
                leftLowIndex++;
            } else {
                array[arrayLowIndex] = rightArray[rightLowIndex-mid-1];
                rightLowIndex++;
            }
            arrayLowIndex++;
        }

        while (leftLowIndex <= mid) {
            array[arrayLowIndex] =leftArray[leftLowIndex-low];
            leftLowIndex++;
            arrayLowIndex++;
        }
        while (rightLowIndex<= high) {
            array[arrayLowIndex] = rightArray[rightLowIndex-mid-1];
            rightLowIndex++;
            arrayLowIndex++;
        }
    }
}
