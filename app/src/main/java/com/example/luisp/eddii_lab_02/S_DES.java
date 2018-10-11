package com.example.luisp.eddii_lab_02;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class S_DES {
    //Declaración de variables
    private static Byte S_box1 [][] = new Byte[4][4];
    private static  Byte[][]  S_box2  = new Byte[4][4];
    private static Byte MasterKey [] = new Byte[10];


    public static void Encryption(String Path){


        //------------------------ S-Box1
        S_box1[0][0]= 01;
        S_box1[0][1]= 00;
        S_box1[0][2]= 11;
        S_box1[0][3]= 10;
        S_box1[1][0]= 11;
        S_box1[1][1]= 10;
        S_box1[1][2]= 01;
        S_box1[1][3]= 00;
        S_box1[2][0]= 00;
        S_box1[2][1]= 10;
        S_box1[2][2]= 01;
        S_box1[2][3]= 11;
        S_box1[3][0]= 11;
        S_box1[3][1]= 01;
        S_box1[3][2]= 11;
        S_box1[3][3]= 10;


        //----------S-Box2
        S_box1[0][0]= 00;
        S_box1[0][1]= 01;
        S_box1[0][2]= 10;
        S_box1[0][3]= 11;
        S_box1[1][0]= 10;
        S_box1[1][1]= 00;
        S_box1[1][2]= 01;
        S_box1[1][3]= 11;
        S_box1[2][0]= 11;
        S_box1[2][1]= 00;
        S_box1[2][2]= 01;
        S_box1[2][3]= 00;
        S_box1[3][0]= 10;
        S_box1[3][1]= 01;
        S_box1[3][2]= 00;
        S_box1[3][3]= 11;


        File OriginalFile = new File(Path);
            int BlockSize = 4*1024;
            int interationNum;
            if(OriginalFile.length() < BlockSize ){
                interationNum = 1;
            }else if (OriginalFile.length() % BlockSize == 0){
                interationNum = (int)OriginalFile.length() / BlockSize;
            }else{
                interationNum = ((int)OriginalFile.length() / BlockSize) + 1;
            }
            while(interationNum-- >  0){
                if(interationNum == 0){
                    BlockSize = (int)OriginalFile.length() % BlockSize;
                }

                byte[] result = new byte[(int)OriginalFile.length()];
                try {
                    InputStream input = null;
                    try {
                        int totalBytesRead = 0;
                        input = new BufferedInputStream(new FileInputStream(OriginalFile));
                        while(totalBytesRead < result.length){
                            //int bytesRemaining = result.length - totalBytesRead;
                            int bytesRead = input.read(result, totalBytesRead, BlockSize);
                            if (bytesRead > 0){
                                totalBytesRead = totalBytesRead + bytesRead;
                            }
                        }

                    }
                    finally {

                        input.close();
                    }
                }
                catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

                byte[] outPut = new byte[(int)result.length];
                for (int i = 0; i < outPut.length ; i++){
                    //escribo el descifrado por funcion
                }





    }

    //Convertidor de Bytes a Bits


    public static void ObtainKey(String aKey){
        int i = 0;
        while(i < aKey.length()){
            if(aKey.charAt(i) == '1'){
                MasterKey[i] = 1;
                i++;
            }else{
                MasterKey[i] = 0;
                i++;
            }
        }



    }


}
