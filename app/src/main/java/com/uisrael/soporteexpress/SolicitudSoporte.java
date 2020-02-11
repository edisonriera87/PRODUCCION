package com.uisrael.soporteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SolicitudSoporte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_soporte);
    }


    public void buscar_direccion(View v)
    {
        Intent intent = new Intent(this, UbicacionSoporte.class);
        //intent.putExtra("codPersona", jsonObject.optString("codPersona"));
        startActivity(intent);
    }
}

