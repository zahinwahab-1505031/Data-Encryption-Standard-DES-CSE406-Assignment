/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataencryptionstandard;

import java.util.Arrays;

/**
 *
 * @author Zahin
 */
public class DES {
   static int []PI = {58, 50, 42, 34, 26, 18, 10, 2,
      60, 52, 44, 36, 28, 20, 12, 4,
      62, 54, 46, 38, 30, 22, 14, 6,
      64, 56, 48, 40, 32, 24, 16, 8,
      57, 49, 41, 33, 25, 17, 9, 1,
      59, 51, 43, 35, 27, 19, 11, 3,
      61, 53, 45, 37, 29, 21, 13, 5,
      63, 55, 47, 39, 31, 23, 15, 7};
    
  static  int []CP_1 = {57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4};
static  int []CP_2 = {14, 17, 11, 24, 1, 5, 3, 28,
        15, 6, 21, 10, 23, 19, 12, 4,
        26, 8, 16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55, 30, 40,
        51, 45, 33, 48, 44, 49, 39, 56,
        34, 53, 46, 42, 50, 36, 29, 32};
  static int [] E = {32, 1, 2, 3, 4, 5,
     4, 5, 6, 7, 8, 9,
     8, 9, 10, 11, 12, 13,
     12, 13, 14, 15, 16, 17,
     16, 17, 18, 19, 20, 21,
     20, 21, 22, 23, 24, 25,
     24, 25, 26, 27, 28, 29,
     28, 29, 30, 31, 32, 1};
   
 static  int []PI_2 = {35, 38, 46, 6, 43, 40, 14, 45,
		33, 19, 26, 15, 23, 8, 22, 10, 
		12, 11, 5, 25, 27, 21, 16, 31,
		28, 32, 34, 24, 9, 37, 2, 1};
 static  int []P = {16, 7, 20, 21, 29, 12, 28, 17,
     1, 15, 23, 26, 5, 18, 31, 10,
     2, 8, 24, 14, 32, 27, 3, 9,
     19, 13, 30, 6, 22, 11, 4, 25};
   
  static int []PI_1 = {40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25};
   static int []SHIFT = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
   
    public static int[] getModifiedKey(String key){
            char []charKey = key.toCharArray();
            int []data = new int[64];
            int count = 0;
            for(int it2=0;it2<charKey.length;it2++){
                int dec = charKey[it2];
                int []binary = DES_Utility.convertToBinary(dec);
                
                for(int it3=0;it3<binary.length;it3++){
                    data[count++] = binary[it3];
                }
            }
            //System.out.println(key);
            //DES_Utility.printArray(data, data.length);
           
            int []modified_key = new int[56];
            for(int it2=0;it2<modified_key.length;it2++){
              modified_key[it2] = data[CP_1[it2]-1];
            }
            //System.out.println("Modified shortened key (56 bits) : ");
            //DES_Utility.printArray(modified_key, modified_key.length);
            //System.out.println("=================================================");
            return modified_key;
                    
        
    }
    public static int[] getLeftRotate(int []arr,int n){
        for(int i=0;i<n;i++){
            int temp = arr[0];
            for(int j=0;j<arr.length-1;j++){
                arr[j] = arr[j+1];
            }
            arr[arr.length-1] = temp;
        }
       // //DES_Utility.printArray(arr, arr.length);
        return arr;
    }
     public static int[] getRightRotate(int []arr,int n){
        for(int i=0;i<n;i++){
            int temp = arr[arr.length-1];
            for(int j=arr.length-2;j>=0;j--){
                arr[j+1] = arr[j];
            }
            arr[0] = temp;
        }
        
        return arr;
    }
    public static int[] getKeyForEachRound(int []key,int round,boolean isEncrpyt){
        int []left = Arrays.copyOfRange(key, 0, key.length/2);
        int []right = Arrays.copyOfRange(key, key.length/2, key.length);
        int []left_after_rotation;
         int []right_after_rotation;
        if(isEncrpyt==true){
        left_after_rotation = getLeftRotate(left, SHIFT[round]);
        right_after_rotation = getLeftRotate(right, SHIFT[round]);
        }
        else {
        left_after_rotation = getLeftRotate(left, SHIFT[SHIFT.length-1-round]);
        right_after_rotation = getLeftRotate(right, SHIFT[SHIFT.length-1-round]);
        }
        int length = left_after_rotation.length + right_after_rotation.length;
        int[] result = new int[length];
        System.arraycopy(left_after_rotation, 0, result, 0, left_after_rotation.length);
        System.arraycopy(right_after_rotation, 0, result, left_after_rotation.length, right_after_rotation.length);
       // //DES_Utility.printArray(result, result.length);
        int []finalKey = new int[48];
        for(int it2=0;it2<finalKey.length;it2++){
              finalKey[it2] = result[CP_2[it2]-1];
        }
        //System.out.println("KEY for ROUND "+ round+1);
        //DES_Utility.printArray(key, key.length);
        return finalKey;
    }
    public static int[] getXOR(int []A,int []B){
        int []result = new int[A.length];
        for(int i=0;i<A.length;i++) {
            if(A[i]==0&&B[i]==0) result[i] = 0;
            else if(A[i]==0&&B[i]==1) result[i] = 1;
            else if(A[i]==1&&B[i]==0) result[i] = 1;
            else if(A[i]==1&&B[i]==1) result[i] = 0;
            
            
            
        }
        return result;
    }
    public static int[] Iterate(int []arr,int []Key,int round,boolean isEncrypt){ // round ta new round
        int []keyForThisIter = getKeyForEachRound(Key, round,isEncrypt);
        int []finalArr = new int[arr.length];
        int []left = Arrays.copyOfRange(arr, 0, arr.length/2);
        int []right = Arrays.copyOfRange(arr, arr.length/2, arr.length);
        System.arraycopy(right,0,finalArr,0,right.length); //left = right
        //System.out.println("RIGHT is copied exactly to the LEFT: ");
        //DES_Utility.printArray(finalArr,finalArr.length);
        int []expandedPrevRight =new int[E.length];
        for(int i=0;i<expandedPrevRight.length;i++){
            expandedPrevRight[i] = right[E[i]-1];
        }
        //System.out.println("After expanding RIGHT of previous iteration using E: ");
        //DES_Utility.printArray(expandedPrevRight,expandedPrevRight.length);
        int []XOR = getXOR(expandedPrevRight,keyForThisIter);
        //System.out.println("After XORing expanded Previous Right and key (both 48 bits): ");
        //DES_Utility.printArray(XOR,XOR.length);
        int []shortenResult = new int[PI_2.length];
        for(int i=0;i<shortenResult.length;i++){
            shortenResult[i] = XOR[PI_2[i]-1];
        }
        //System.out.println("After shortening result to 32bits again: ");
        //DES_Utility.printArray(shortenResult,shortenResult.length);
        //p box
        int []output_Pbox = new int[shortenResult.length];
        for(int i=0;i<output_Pbox.length;i++){
            output_Pbox[P[i]-1] = shortenResult[i];
            
        }
        
        //System.out.println("After passing this shortenend XOR result through Pbox: ");
        //DES_Utility.printArray(output_Pbox, output_Pbox.length);   
        int []XOR2 = getXOR(left, output_Pbox);
        //System.out.println("After XORing previous LEFT and the output from PBox: ");
        //DES_Utility.printArray(XOR2, XOR2.length);
        System.arraycopy(XOR2,0, finalArr, right.length, XOR2.length);
        //System.out.println("FINAL DATA AFTER ITERATION: ");
        //DES_Utility.printArray(finalArr, finalArr.length);
        return finalArr;
        //left er sathe xor
    }
    public static String Encrypt(String plainText,String key,boolean isEncrypt){
        String []parts = DES_Utility.divide_into_equal_length(plainText, 8);
        String cipheredText = "";
       for(int it1=0;it1<parts.length;it1++) {
     // for(int it1=0;it1<1;it1++) {
            char []charData = parts[it1].toCharArray();
            int []data = new int[64];
//            String str ="0011010000101100110011001101111111010001011000000000100101010010";
//            char []bla = str.toCharArray();
//            for(int i=0;i<64;i++) data[i] = bla[i]-'0';
            int count = 0;
            for(int it2=0;it2<charData.length;it2++){
                int dec = charData[it2];
                int []binary = DES_Utility.convertToBinary(dec);
                
                for(int it3=0;it3<binary.length;it3++){
                    data[count++] = binary[it3];
                }
            }
            //System.out.println(parts[it1]);
            //DES_Utility.printArray(data, data.length);
            
            int []transposed_data = new int[64];
            for(int it2=0;it2<transposed_data.length;it2++){
                transposed_data[it2] = data[PI[it2]-1];
            }
            //System.out.println("Transposed Data after applying PI matrix: ");
            //DES_Utility.printArray(transposed_data, transposed_data.length);
            //System.out.println("================================================");
            int []backupData = Arrays.copyOf(transposed_data, transposed_data.length);
            int []shortenedKey = getModifiedKey(key);
            
            for(int round=0;round<16;round++){
                //System.out.println("==================ROUND NO: "+ round+" ====================");
              transposed_data =  Iterate(transposed_data, shortenedKey, round,isEncrypt);
                //System.out.println("==========================================================");
            }
            //System.out.println("After 16 iterations data becomes:    ");
            //DES_Utility.printArray(transposed_data, transposed_data.length);
            
            int []finalData = new int[data.length];
            System.arraycopy(transposed_data, transposed_data.length/2, finalData, 0, transposed_data.length/2);
            System.arraycopy(transposed_data, 0, finalData, finalData.length/2, transposed_data.length/2);
            //System.out.println("After making LEFT -> RIGHT AND RIGHT->LEFT new DATA: ");
            //DES_Utility.printArray(finalData, finalData.length);
            int []afterTranspose_finalData = new int [PI_1.length];
            for(int it2=0;it2<afterTranspose_finalData.length;it2++){
                afterTranspose_finalData[it2] = finalData[PI_1[it2]-1];
            }
            //System.out.println("After permuting again using PI_2 Matrix: ");
            //DES_Utility.printArray(afterTranspose_finalData, afterTranspose_finalData.length);
         //   //System.out.println("CIPHERED TEXT:   ");
            for(int it2=0;it2<8;it2++){
                int []bin = Arrays.copyOfRange(afterTranspose_finalData, it2*8,it2*8+8);
                int dec = DES_Utility.convertToDecimal(bin, 8);
                char c = (char) dec;
                cipheredText += c;
              //  System.out.print(c);
                
                
            }
            //System.out.println("\n----------------------------------------");
        }  
        if(isEncrypt==true)System.out.println("CIPHERED TEXT:  ");
        else System.out.println("DECIPHERED TEXT:    ");
        System.out.println(cipheredText);
        return cipheredText;
        
        
    }
    
    
}
