package com.example.luisp.eddii_lab_02;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ZigZag {

    private static boolean isCode = true;

    public static void Encryption(String aText, int aLevel){
        int key = aLevel;
        int length = aText.length();
        char mat[][] = new char [key][length];
        String result ="";
        char prueba = 0;

        //creo la matriz llena de null
        for (int i = 0;i < key; i++){
            for (int j = 0 ; j < length; j++){
                mat [i][j] = '\u0000';
            }
        }

        int row = 0;
        int check = 0;

        //lleno la matriz con los caracteres en diagonal
        for(int i = 0 ; i < length; i++){
            if(check == 0){
                mat[row][i] = aText.charAt(i);
                row++;
                //cuando row = key significa que llego al final de la diagonal
                if(row == key){
                    check = 1;
                    row--;
                }

            }else if (check == 1){
                row--;
                mat[row][i] = aText.charAt(i);
                //cuando row = 0 llego al inicio de la diagonal
                if(row == 0){
                    check = 0;
                    row = 1;
                }

            }
        }
        for(int i = 0; i < key; i++){
            for(int j = 0; j < length; j++){
                //creo la cadena encriptada ignorando los null en la matriz
                if(mat[i][j] != '\u0000'){
                    result += (mat[i][j]);
                }

            }
        }
        //creo el archivo
        createFile(result);
        //como el archivo encriptado ya fue creado pasa a ser falso
        isCode = false;
    }

    public static void Decryption(String aText, int aLevel){
        int key = aLevel;
        int length = aText.length();
        char mat[][] = new char [key][length];
        String result ="";

        for (int i = 0;i < key; i++){
            for (int j = 0 ; j < length; j++){
                mat [i][j] = '\u0000';
            }
        }

        int row = 0;
        int check = 0;
        //agrego el texto encriptado en zig zag
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


        //Cambio el orden de los rieles para obtener el zig zag original
        int ordr = 0;
        for(int i = 0; i< key;i++){
            for(int j = 0; j < length; j++){
                String temp = mat[i][j] + "";
                if(temp.matches("\u0000")){
                    //se salta en caso de  \u0000
                    continue;
                }else{
                    //agrego las letras una por una en diagonal
                    mat[i][j] = aText.charAt(ordr);
                    ordr++;
                }
            }
        }

        String decryptText = "";

        check = 0;
        row = 0;
        // recorro la matriz ya con las olas originales para devolver el texto original
        for (int i = 0; i < aText.length(); i++){
            if(check == 0){
                decryptText  += mat[row][i];
                row++;
                if(row == key){
                    check = 1;
                    row--;
                }

            }else if (check == 1){
                row--;
                decryptText  += mat[row][i];
                if(row == 0){
                    check = 0;
                    row = 1;
                }

            }

        }
        //creo el archivo de salida
        createFile(decryptText);



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
