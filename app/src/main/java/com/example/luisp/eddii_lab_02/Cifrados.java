package com.example.luisp.eddii_lab_02;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Cifrados extends AppCompatActivity {
    private Button btnUpload;
    private Button btnInit;

    String texto = "";
    String Name = "";
    Integer tamaño;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cifrados);

        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnInit = (Button) findViewById(R.id.btnInit);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intent,"Seleccione un Archivo"),123);
            }
        });
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ZigZag.Encryption("Hola mundo",3);
                    ZigZag.Decryption("H doamnolu",3);
                    Termino();

            }
        });

        RequestPermission();
    }
    public void Termino(){
        Toast.makeText(this,"Proceso finalizado con exito",Toast.LENGTH_LONG).show();
    }
    public void RequestPermission(){

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(Cifrados.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Cifrados.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(Cifrados.this,
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
                tamaño =  texto.getBytes().length;
                Name = selectedFile.getLastPathSegment();


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
