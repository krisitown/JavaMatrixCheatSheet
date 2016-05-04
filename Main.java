package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //the coordinates start from 0
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String[] strDim = scn.nextLine().split("\\s+");

        char[][] matrix = new char[Integer.parseInt(strDim[0])][Integer.parseInt(strDim[1])];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = scn.next().charAt(0); //nextLine().charAt(0);
            }
        }

        printMatrix(matrix);

        //bomb the matrix [Алаху е на снакбар]
        System.out.println("Bombard at: ");
        int rowToAttack = scn.nextInt();
        int colToAttack = scn.nextInt();
        System.out.println("With radius: ");
        int radius = scn.nextInt();
        bombMatrix(rowToAttack, colToAttack, matrix, radius);

        printMatrix(matrix);

        //find coords of the first occurrence of value
        System.out.println("Find coords of: ");
        char value = scn.next().charAt(0);

        //compute the value beforehand in order to save a cycle
        int[] coords = findFirstCoordsOf(value, matrix);
        System.out.printf("Row:[%d] Col:[%d]\n", coords[0], coords[1]);

        //find all coordinates of
        System.out.println("Find all coordinates of: ");
        value = scn.next().charAt(0);
        ArrayList<int[]> coordinates = findCoordsOf(matrix, value);
        for (int[] coord : coordinates) {
            System.out.printf("Row:[%d] Col:[%d]\n", coord[0], coord[1]);
        }

    }

    //gets element at index
    private static char getAtIndex(int row, int col, char[][] matrix){
        return matrix[row][col];
    }

    //sets the value of a cell based on coordinates row and col
    private static void setAtIndex(int row, int col, char[][] matrix, char value){
        matrix[row][col] = value;
    }

    //finds the coordinates of the first occurrence of given value [use for unqie values]
    //returns null in the case where it cannot find the value
    private static int[] findFirstCoordsOf(char value, char[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == value){
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }

    //finds all of the coordinates of a certain value
    private static ArrayList<int[]>findCoordsOf(char[][] matrix, char value){
        ArrayList<int[]> coordinates = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == value) {
                    coordinates.add(new int[]{row, col});
                }
            }
        }
        return coordinates;
    }

    //bombards the area at coordinates row and col [crosslike] with given radius
    private static void bombMatrix(int row, int col, char[][] matrix, int radius){
        if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length){
            matrix[row][col] = '\0';
        }
        for (int i = 1; i <= radius; i++) {
            if(row + i >= 0 && col >= 0 && row + i < matrix.length && col < matrix[0].length){
                matrix[row+i][col] = '\0';
            }
            if(row - i >= 0 && col >= 0 && row - i < matrix.length && col < matrix[0].length){
                matrix[row-i][col] = '\0';
            }
            if(row >= 0 && col + i >= 0 && row < matrix.length && col + i < matrix[0].length){
                matrix[row][col + i] = '\0';
            }
            if(row >= 0 && col - i >= 0 && row < matrix.length && col - i < matrix[0].length){
                matrix[row][col - i] = '\0';
            }
        }
    }

    //prints a matrix
    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
