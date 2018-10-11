package com.example.luisp.eddii_lab_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class Menu extends AppCompatActivity {

    ImageButton btnZigZag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnZigZag = (ImageButton)findViewById(R.id.btnZigZag);


        btnZigZag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnZigZag = new Intent(Menu.this,Cifrados.class);
                startActivity(btnZigZag);
            }
        });


    }
}
