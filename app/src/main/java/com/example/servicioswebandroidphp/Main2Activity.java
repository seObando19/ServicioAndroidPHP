package com.example.servicioswebandroidphp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    //Recibir el dato del nombre del usuario
    public static final String nombre="nombree";
    public static final String usuario="usr";
    TextView nombreu,usru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nombreu=findViewById(R.id.tvnombreusuario);
        usru=findViewById(R.id.tvusru);

        nombreu.setText("Bienvenido/a:"+getIntent().getStringExtra("nombree"));
        usru.setText("Usuario:"+getIntent().getStringExtra("usr"));
    }
}
