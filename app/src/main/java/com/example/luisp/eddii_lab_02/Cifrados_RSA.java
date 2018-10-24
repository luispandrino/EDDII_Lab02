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
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


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
                if (!rsa.esPrimo(Integer.parseInt(String.valueOf(txtq.getText()))) || !rsa.esPrimo(Integer.parseInt(String.valueOf(txtp.getText())))){
                    
                }
            }
        });



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
