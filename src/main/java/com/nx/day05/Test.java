package com.nx.day05;

import java.util.Arrays;


public class Test {

    public static void main(String[] args) {
        Integer[] arry = GenericsArray.getArray(Integer.class,10);
        System.out.println(Arrays.toString(arry));
        for (int i = 0; i < arry.length; i++) {
            arry[i]=i;
        }
        System.out.println(Arrays.toString(arry));
        Integer[][] arr2 = GenericsArray.getArray(Integer.class,2,2);
        for (int i = 0; i < 2; i++) {
            for(int j=0;j<2;j++){
                arr2[i][j] = (i+1)*(j+1);
            }
        }
        for (int i = 0; i < 2; i++) {
            for(int j=0;j<2;j++){
                System.out.println(arr2[i][j]);
            }
        }
    }
}
