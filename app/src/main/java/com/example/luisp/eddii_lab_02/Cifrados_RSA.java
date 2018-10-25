package com.example.luisp.eddii_lab_02;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Cifrados_RSA extends AppCompatActivity {

    private EditText txtq;
    private EditText txtp;
    private Button btnKeys;
    private Button btnContenido;
    private Button btnContenidoDes;
    private Button btnPublickey;
    private Button btnPrivatekey;
    private Button btnCifrar;
    private Button btnDescifrar;
    String texto = "";
    String Name = "";
    String aux ="";
    String aux2 ="";
    String[] llavepub = new String[2];
    String[] llavepriv = new String[2];
    boolean flagpub = true;
    boolean flagpriv = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cifrados__rs);

        txtq = (EditText) findViewById(R.id.txtq);
        txtp = (EditText) findViewById(R.id.txtp);
        btnKeys = (Button) findViewById(R.id.btnKeys);
        btnContenido = (Button) findViewById(R.id.btnContenido);
        btnContenidoDes = (Button) findViewById(R.id.btnCotenidoDes);
        btnPublickey = (Button) findViewById(R.id.btnPublicKey);
        btnPrivatekey = (Button) findViewById(R.id.btnPrivatekey);
        btnCifrar = (Button) findViewById(R.id.btnCifrar);
        btnDescifrar = (Button) findViewById(R.id.btnDescifrar);




        btnKeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RSA rsa = new RSA(Integer.parseInt(String.valueOf(txtq.getText())) , Integer.parseInt(String.valueOf(txtp.getText())));
                Termino();

            }
        });

        btnPublickey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Seleccione un Archivo"),123);
            }
        });

        btnContenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Seleccione un Archivo"),123);


            }
        });

        btnPrivatekey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Seleccione un Archivo"),123);
                flagpriv = true;
            }
        });
        btnDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llavepriv = aux2.split(",");
                int c = Integer.parseInt(llavepriv[0]);//n
                int d = Integer.parseInt(llavepriv[1]);//d
                BigInteger n = new BigInteger(""+ c);
                BigInteger D = new BigInteger(""+ d);
                String[] Encript = (texto.split(","));

                BigInteger N;

                int [] Auxiliar = new int[Encript.length];
                for (int i = 0; i< Encript.length;i++){
                    Auxiliar[i] = Integer.parseInt(Encript[i]);
                }

                BigInteger[] Cifrado = new BigInteger[Encript.length];



                for(int i = 0; i < Encript.length; i++){
                    Cifrado[i] = BigInteger.valueOf(Auxiliar[i]);
                }


                RSA rsa = new RSA();
                //Decrypt
                String Output = rsa.decrypt(Cifrado,D,n);

                rsa.createFile(Output);
                Termino();
            }
        });
        btnContenidoDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Seleccione un Archivo"),123);


            }
        });
        btnCifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llavepub = aux.split(",");

                int a = Integer.parseInt(llavepub[0]);//n
                int b = Integer.parseInt(llavepub[1]);//E

                String teststring;
                // encrypt
                RSA rsa = new RSA();

                BigInteger N = new BigInteger(""+ a);
                BigInteger E = new BigInteger(""+ b);

                BigInteger[] encrypted = rsa.encrypt(texto,E,N);
                teststring = new String(encrypted.toString());
                String cif[] = new String[encrypted.length];

                for (int i = 0; i<encrypted.length;i++){
                    cif[i]=encrypted[i].toString();
                }
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
                    for(int i = 0; i < encrypted.length;i++){
                        bw.write(cif[i] + ",");
                    }

                    bw.close();

                }catch (IOException ioe) {
                    ioe.printStackTrace();
                    Error();
                }

                flagpriv = true;
                Termino();




            }
        });

    }
    public void Error(){
        Toast.makeText(this,"Seleccione una de las dos opciones antes de cifrar / descifrar/ o verifque que la clave solo contenga 1 o 0 y sea de 10 digios",Toast.LENGTH_LONG).show();
    }
    public void Termino(){
        Toast.makeText(this,"Proceso finalizado con exito",Toast.LENGTH_LONG).show();
    }

    public void RequestPermission(){

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Cifrados_RSA.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Cifrados_RSA.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(Cifrados_RSA.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 123 && resultCode == RESULT_OK){
            Uri selectedFile = data.getData();
            Toast.makeText(this,selectedFile.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(this,selectedFile.getPath(),Toast.LENGTH_LONG).show();



            try{


                ReadText(selectedFile);
                texto = ReadText(selectedFile);
                Name = selectedFile.getLastPathSegment();
                if(flagpub){
                    aux = ReadText(selectedFile);
                }
                if(flagpriv){
                    aux2 = ReadText(selectedFile);
                }
                flagpub = false;
                flagpriv = false;


            }catch (IOException e)
            {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

    }




    private String ReadText(Uri uri) throws IOException {
        InputStream Is =   getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Is));
        StringBuilder stringBuilder = new StringBuilder();
        String Line;
        while ((Line = reader.readLine())!= null){
            stringBuilder.append(Line);


        }
        Is.close();
        reader.close();
        return stringBuilder.toString();



    }
}
