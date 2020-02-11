package com.uisrael.soporteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ingresoLogin(View v)

    {
        Intent abrirActividad = new Intent(MainActivity.this ,Login.class);
        startActivity(abrirActividad);

    }

    public void ingresoRegistro(View v)

    {
        Intent abrirActividad = new Intent(MainActivity.this ,registro_usuarios.class);
        startActivity(abrirActividad);

    }
}
