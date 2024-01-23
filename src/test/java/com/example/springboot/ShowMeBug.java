package com.example.springboot;

import java.util.Arrays;

public class ShowMeBug {

    public static void main(String[] args) {
        int[]   nums   = {2, 2, 7, 11, 15, 16};
        int[][] result = countSum(nums, 9);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] countSum(int[] nums, int sum) {
        int[][] result = new int[100][100];
        if (nums == null || nums.length == 0) {
            return result;
        }

        int index = 0;
        int left  = 0;
        int right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            if (nums[left] + nums[right] > sum) {
                right--;
            } else if (nums[left] + nums[right] < sum) {
                left++;
            } else {
                result[index][0] = left;
                result[index][1] = right;

            }
        }
        return result;
    }
}