/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataencryptionstandard;

import java.util.Scanner;

/**
 *
 * @author Zahin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
       String Key = "";
      String Plaintext= "";
        System.out.println("Enter key: ");
      if(sc.hasNextLine()) Key = sc.nextLine();
        System.out.println("Enter plain text: ");
        if(sc.hasNextLine()) Plaintext = sc.nextLine();
     //   String Key = "cse_buet";
     //   String Plaintext = "Attack_at_dawn!";

        String ciphered = DES.Encrypt(Plaintext, Key,true);
        String deciphered = DES.Encrypt(ciphered, Key,false);
       
       
    }
    
}
