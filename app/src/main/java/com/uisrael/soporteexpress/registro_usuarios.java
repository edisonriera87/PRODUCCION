package com.uisrael.soporteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registro_usuarios extends AppCompatActivity {

private EditText nombre,apellido,correo,telefono,cedula,rol;
private Button btnregistrar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        nombre=findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtapellido);
        correo=findViewById(R.id.txtcorreo);
        telefono=findViewById(R.id.txtfono);
        cedula=findViewById(R.id.txtcedula);
        //rol = findViewById(R.id.txtrol);



//        btnregistrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                consumirServicio();
//            }
//        });


    }

    public void consumirServicio(View v){
        // ahora ejecutaremos el hilo creado

        String codPersona= cedula.getText().toString();
        String nombres= nombre.getText().toString();
        String apellidos= apellido.getText().toString();
        String email= correo.getText().toString();
        String telefono= "022 555 222";
        String codRol= "admin";

        servicioRegistro servicioTask= new servicioRegistro(this,"http://apiandroid.netfly.net.ec/api/personas",codPersona,nombres,apellidos,email,telefono,codRol);
        servicioTask.execute();



    }



}
