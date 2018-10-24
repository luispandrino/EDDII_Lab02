package com.example.luisp.eddii_lab_02;


import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p;

    private BigInteger q;

    private BigInteger N;

    private BigInteger phi;

    private BigInteger e;

    private BigInteger d;

    private int bitlength = 1024;

    private Random r;



    public RSA()

    {

        r = new Random();

        p = BigInteger.probablePrime(bitlength, r);

        q = BigInteger.probablePrime(bitlength, r);

        N = p.multiply(q);

        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitlength / 2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)

        {

            e.add(BigInteger.ONE);

        }

        d = e.modInverse(phi);

    }


    public RSA(BigInteger e, BigInteger d, BigInteger N)

    {

        this.e = e;

        this.d = d;

        this.N = N;

    }



    @SuppressWarnings("deprecation")




    public  String bytesToString(byte[] encrypted)

    {

        String test = "";

        for (byte b : encrypted)

        {

            test += Byte.toString(b);

        }

        return test;

    }



    // Encrypt message

    public byte[] encrypt(byte[] message)

    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();


    }



    // Decrypt message

    public byte[] decrypt(byte[] message)

    {

        return (new BigInteger(message)).modPow(d, N).toByteArray();

    }

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
}
