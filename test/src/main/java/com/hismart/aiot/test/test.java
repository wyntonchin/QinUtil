package com.hismart.aiot.test;

public class test {

    //逆时针旋转矩阵
    static void rotateMatrix2(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        if(m !=n){
            return;
        }

        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[n-j-1][i] =matrix[i][j];//matrix 遍历，赋值给
            }
        }
        print(result);
    }

    //顺时针旋转矩阵
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

        rotateMatrix2(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }
}
