/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataencryptionstandard;

/**
 *
 * @author Zahin
 */
public class DES_Utility {

    public static int[] convertToBinary(int n) {
        int[] binaryString = new int[8];

        int count = 7;
        while (count >= 0) {
            int bit = n % 2;
            binaryString[count] = bit;
            count--;
            n = n / 2;
        }
     //  for(int i=0; i<8; i++) System.out.println(binaryString[i]);
       return binaryString;

    }
    public static int convertToDecimal(int []binary,int n)
    {
        int decimal = 0;

       // Initializing base value to 1, i.e 2^0
       int base = 1;

       int len = n;
       for (int i=len-1; i>=0; i--)
       {
           if (binary[i] == 1)
               decimal += base;
           base = base * 2;
       }

       return decimal;
    }
    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + "");
        }
        System.out.println("");
    }

    public static String[] divide_into_equal_length(String string, int length) {

        double nprime = string.length() / (length * 1.0);
        int n = (int) Math.ceil(nprime);
        String[] parts = new String[n];
        char[][] matrix = new char[n][length];
        int counter = 0;
        char c;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < length; j++) {
                if (counter < string.length()) {
                    matrix[i][j] = string.charAt(counter++);
                } else {
                    break;
                }

            }
            parts[i] = new String(matrix[i]);
            //   System.out.println(matrix[i]);

        }

        return parts;
    }
}
