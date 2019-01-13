/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Jon Gonzalez, Lander Lluvia
 */
public class Cifrado {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args, String password){
        //String text;
        FileInputStream fispublic;
        byte[] key;
        KeyFactory keyFactory;
        PublicKey publicKey;
        Cipher cipher;
        FileOutputStream fos;
        /*do{
            System.out.println("Escribe el texto a cifrar:");
            text = introducirCadena();
        }while(text == null);*/
        try {
            //open the stream for read the public key file 
            fispublic = new FileInputStream("public.key");
            //set the size for the byte array
            key = new byte[fispublic.available()];
            //read the file
            fispublic.read(key);
            //close the stream
            fispublic.close();
            //instance the factory with the rsa algorithm
            keyFactory = KeyFactory.getInstance("RSA");
            //generate the public key from the information of the file
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(key));
            //instance the cipher object with the algorithm rsa
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //intialize the cipher object for the encrypt mode with the public key
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //open the stream for write the text encrypted
            fos = new FileOutputStream("file.dat");
            //write the text encrypted
            fos.write(cipher.doFinal(password.getBytes()));
            //close the stream
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(
                    Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NoSuchAlgorithmException | 
                InvalidKeySpecException | NoSuchPaddingException | 
                InvalidKeyException | IllegalBlockSizeException | 
                BadPaddingException ex) {
            Logger.getLogger(
                    Cifrado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @return The string readed
     */
    /*public static String introducirCadena(){
        String cadena;
        InputStreamReader entrada =new InputStreamReader(System.in);
        BufferedReader teclado= new BufferedReader(entrada);
        try{
                cadena=teclado.readLine();
        }catch(IOException er){
                System.out.println("error al introducir datos");
                cadena = null;
        }
        return cadena;
    }*/
}
