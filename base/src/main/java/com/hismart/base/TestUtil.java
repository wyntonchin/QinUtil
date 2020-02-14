package com.hismart.base;

import android.content.Context;
import android.text.TextUtils;

/**
 * @author qinwendong
 * @date 2020/2/13
 * descrption:
 */
public class TestUtil {
    private static TestUtil mSingleTon;
    private TestUtil(){

    }

    public static TestUtil getInstance(){
        if(mSingleTon == null){
            synchronized (mSingleTon){
                if(mSingleTon == null){
                    mSingleTon = new TestUtil();
                }
            }
        }
        return mSingleTon;
    }


    private static Context contxt;

    private TestUtil(Context contxt){
        contxt = contxt;
    }


    static class Inner{
        private static TestUtil mSingleTon = new TestUtil(contxt);
    }

    public static TestUtil getInstance2(Context contxt){
        contxt = contxt;
        return Inner.mSingleTon;
    }

    int lengthOfLIS(int[] a){
        int[] lis = new int[a.length];
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            //记录i位置数得前面升序数
            lis[i]= 1;
            for (int j = 0; j < i; j++) {
                //首先是j要比i小，那么至少有 j+1个
                if( a[i]> a[j] && lis[i]<lis[j]+1){
                    lis[i] = lis[j] + 1;//那么i就增加了一个
                }
            }

            result = result >lis[i]?result: lis[i];

        }
        return result;

    }


    /**
     * 二分法，插入key到数组得位置
     * @param a
     * @param key 传入的关键参数
     * @return
     */
    int binarySearch(int[] a,int key){
        int low = 0;
        int heigh = a.length - 1;
        int middle;

        while (low < heigh){
            middle = low + (low + heigh) / 2;
            if(a[middle] > key){
                heigh = heigh - 1;
            }else if(a[middle] < key){
                low = low + 1;
            }else {
                return middle;
            }
        }
        return low;
    }


    static void rotateMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        if(m !=n){
            return;
        }

        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n-1-i] =matrix[i][j];//matrix 遍历，赋值给
            }
        }
        print(result);
    }

    private static void print(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        rotateMatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

}
