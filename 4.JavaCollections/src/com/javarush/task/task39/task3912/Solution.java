package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static int maxSquare(int[][] matrix) {
        int maxSquare = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    int level = 1;
                    boolean onesOnLevel = true;
                    while (onesOnLevel && level + i < matrix.length && level + j < matrix[i].length) {
                        for (int k = 0; k <= level; k++) {
                            if (matrix[i + level][j + k] == 0 || matrix[i + k][j + level] == 0) {
                                onesOnLevel = false;
                                break;
                            }
                        }
                        if (onesOnLevel) {
                            level++;
                        }
                    }
                    if (maxSquare < level * level)
                        maxSquare = level * level;
                }
            }
        }
        return maxSquare;
    }
}
