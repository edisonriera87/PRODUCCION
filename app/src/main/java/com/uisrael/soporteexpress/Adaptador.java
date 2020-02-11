package com.uisrael.soporteexpress;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adaptador extends BaseAdapter{

    private static LayoutInflater inflater = null;

    Context contexto;
    String[][] datos;
    int[] datosImg;

    public Adaptador(Context conexto, String[][] datos, int[] imagenes)
    {
        this.contexto = conexto;
        this.datos = datos;
        this.datosImg = imagenes;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        TextView nombre =  vista.findViewById(R.id.txtNombre);
        TextView apellido =  vista.findViewById(R.id.txtapellido);
        TextView descripcion = vista.findViewById(R.id.txtdescripcion);

        ImageView imagen =  vista.findViewById(R.id.imgContacto);
        RatingBar calificacion =  vista.findViewById(R.id.ratingBar);


        nombre.setText(datos[i][0]);
        apellido.setText(datos[i][1]);
        descripcion.setText(datos[i][2]);
        imagen.setImageResource(datosImg[i]);
        calificacion.setProgress(Integer.valueOf(datos[i][3]));



//        nombre.setText("Edison");
//        apellido.setText("Riera");
//        descripcion.setText("sin conexion a Internet ");
//        imagen.setImageResource(datosImg[i]);
//        calificacion.setProgress(5);


        imagen.setTag(i);

//        imagen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent visorImagen = new Intent(contexto, VisorImagen.class);
//                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
//                contexto.startActivity(visorImagen);
//            }
//        });


        return vista;
}

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}


