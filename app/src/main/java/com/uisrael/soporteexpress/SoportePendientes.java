package com.uisrael.soporteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class SoportePendientes extends AppCompatActivity {

    ListView lista;
    String[][] datos ={

            {"Nombre","Apellido","Descripcion","10"}

    };

    int[] datosImg = {R.drawable.business_application_addmale_useradd_insert_add_user_client_2312 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte_pendientes);

        lista = (ListView) findViewById(R.id.lvLista);
        lista.setAdapter(new Adaptador(this,datos,datosImg));

    }
}
