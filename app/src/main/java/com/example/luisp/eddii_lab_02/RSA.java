package com.example.luisp.eddii_lab_02;


import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private BigInteger p;

    private BigInteger q;

    private BigInteger N;

    private BigInteger phi;

    private BigInteger e;

    private BigInteger d;

    private int bitlength = 1024;

    private Random r;

    public  RSA() {}



    public RSA(int a , int b)

    {

        if (esPrimo(a) && esPrimo(b) ){
            r = new Random();
            BigInteger tempQ = new BigInteger(""+ a);
            BigInteger tempP = new BigInteger(""+ b);

            q = tempQ;
            p = tempP;

            N = p.multiply(q);

            phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

            e = BigInteger.valueOf(3L);

            while (!phi .gcd(e).equals(BigInteger.ONE))
            {

                e = e.add(BigInteger.ONE);
            }
            this.e = e;
            d = e.modInverse(phi);

            LlavePriv();
            LlavePub();
        }else{

        }


    }


    public boolean esPrimo(int numero){

        Boolean primo = true;
        if(numero<2)
        {
            primo = false;
        }
        else
        {
            for(int x=2; x*x<=numero; x++)
            {
                if( numero%x==0 ){primo = false;break;}
            }
        }
        return primo;

    }




    // Encrypt message

    public BigInteger[] encrypt( String message,BigInteger userE,BigInteger userN)
    {
        int i ;
        byte[] temp = new byte[1] ;


        byte[] digits = message.getBytes() ;

        BigInteger[] bigdigits = new BigInteger[digits.length] ;

        for( i = 0 ; i < bigdigits.length ; i++ )
        {
            temp[0] = digits[i] ;
            bigdigits[i] = new BigInteger( temp ) ;
        }

        BigInteger[] encrypted = new BigInteger[bigdigits.length] ;

        for( i = 0 ; i < bigdigits.length ; i++ )
            encrypted[i] = bigdigits[i].modPow( userE, userN ) ;


        return( encrypted ) ;
    }

    public String decrypt( BigInteger[] encrypted,BigInteger D,BigInteger N )
    {
        int i ;


        BigInteger[] decrypted = new BigInteger[encrypted.length] ;

        for( i = 0 ; i < decrypted.length ; i++ )
            decrypted[i] = encrypted[i].modPow( D, N ) ;

        char[] charArray = new char[decrypted.length] ;

        for( i = 0 ; i < charArray.length ; i++ )
            charArray[i] = (char) ( decrypted[i].intValue() ) ;


        return( new String( charArray ) ) ;
    }
    // Decrypt message


    public void createFile(String Text){

        File F = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MisCifrados","Decode.txt");
        BufferedWriter bw;
        try {
            FileWriter fw = new FileWriter(F);
            bw = new BufferedWriter(fw);
            bw.write(Text);
            bw.close();

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }




    }


    public void LlavePub (){

        File F = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MisCifrados","PublicKey.txt");
        BufferedWriter bw;
        try {
            FileWriter fw = new FileWriter(F);
            bw = new BufferedWriter(fw);
            String llave  =   N.toString() + "," + d.toString() ;
            bw.write(llave);
            bw.close();

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void LlavePriv (){

        File F = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MisCifrados","PrivateKey.txt");
        BufferedWriter bw;
        try {
            FileWriter fw = new FileWriter(F);
            bw = new BufferedWriter(fw);
            String llave  =  N.toString() + "," + e.toString();
            bw.write(llave);
            bw.close();

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}