package com.example.luisp.eddii_lab_02;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ZigZag {
    int levels;
    private static boolean isCode = true;

    public static void Encryption(String aText, int aLevel){
        int key = aLevel;
        int length = aText.length();
        char mat[][] = new char [key][length];
        String result ="";

        for (int i = 0;i < key; i++){
            for (int j = 0 ; j < length; j++){
                mat [i][j] = '.';
            }
        }


        int row = 0;
        int check = 0;

        for(int i = 0 ; i < length; i++){
            if(check == 0){
                mat[row][i] = aText.charAt(i);
                row++;
                if(row == key){
                    check = 1;
                    row--;
                }

            }else if (check == 1){
                row--;
                mat[row][i] = aText.charAt(i);
                if(row == 0){
                    check = 0;
                    row = 1;
                }

            }
        }
        for(int i = 0; i < key; i++){
            for(int j = 0; j < length; j++){

                if(mat[i][j] != '.'){
                    result += (mat[i][j]);
                }

            }
        }
        createFile(result);
        isCode = false;
    }

    private static void createFile(String Text){
        if(isCode){
            File nuevaCarpeta;
            nuevaCarpeta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) , "/MisCifrados");
            if (!nuevaCarpeta.exists()){

                nuevaCarpeta.mkdirs();
            }

            File F = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/MisCifrados","code.txt");
            BufferedWriter bw;
            try {
                FileWriter fw = new FileWriter(F);
                bw = new BufferedWriter(fw);
                bw.write(Text);
                bw.close();

            }catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }else{
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
}
