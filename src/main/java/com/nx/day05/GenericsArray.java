package com.nx.day05;

import java.lang.reflect.Array;

/**
 * 构建泛型数组
 */
public class GenericsArray {

    public static <T> T[] getArray(Class<T> type,int length){
        return (T[])Array.newInstance(type,length);
    }

    public static <T> T[][] getArray(Class<T> type,int rows,int cols){
        return (T[][])Array.newInstance(type,rows,cols);
    }
}
