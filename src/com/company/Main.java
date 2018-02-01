package com.company;

public class Main {

    public static void main(String[] args) {
      MergeSort mergeSort=new MergeSort();
      int[] array={5,4,9,0,8,6,40};
      int ThreadSize= Runtime.getRuntime().availableProcessors();
        System.out.println(" ThreadSize : "+ThreadSize);
      mergeSort.mergeSort(array,ThreadSize);
        for(int index=0;index<array.length;index++){
            System.out.println(array[index]);
        }
    }
}
