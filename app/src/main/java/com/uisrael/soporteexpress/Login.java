package com.uisrael.soporteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private  EditText usuario;
    private TextView pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.txtIdUsuario);
        pass=findViewById(R.id.txtPass);


    }


    public void getDatos(View v) throws IOException {
      //Declaracion de Variables
        final String valorId,valorEmail;
        int banderaUsuario;
        String idBdd,mailBdd;

        //seteo de variables
        banderaUsuario=0;
        valorEmail=usuario.getText().toString();
        valorId = pass.getText().toString();


        banderaUsuario=0;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apiandroid.netfly.net.ec/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        llamado a la funcion

        ServiceUsuarios service = retrofit.create(ServiceUsuarios.class);
        Call<List<ResponseUsuarios>> response = service.getUsersGet();

//        try {
//            for(ResponseUsuarios post: response.execute().body()) {
//
//                idBdd=post.getCodPersona();
//                mailBdd=post.getEmail();
//
//                if (idBdd== valorId && mailBdd==valorEmail)
//                {
//                    banderaUsuario=1;
//                }
//
//
//
//
//            }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

       for(ResponseUsuarios post:response.execute().body())

       {
           String content="";
           content += "userId:" + post.getCodPersona() + "\n";

       }


        response.enqueue(new Callback<List<ResponseUsuarios>>() {
            @Override
            public void onResponse(Call<List<ResponseUsuarios>> call, Response<List<ResponseUsuarios>> response) {
                if(!response.isSuccessful()){

                    return;
                }

                List<ResponseUsuarios> listaUsuarios= response.body();

                for(ResponseUsuarios post:listaUsuarios)
                {

                    if(post.getCodPersona()==valorId && post.getEmail()==valorEmail)
                    {



                    }







                }


            }

            @Override
            public void onFailure(Call<List<ResponseUsuarios>> call, Throwable t) {



            }

            //validacion del usuario para el login



        });





    }


    //metodo login

    public void Login(View v) {
       String valorRol;

        String sql = "http://apiandroid.netfly.net.ec/api/personas";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            json = response.toString();
            JSONArray JSONArr = null;
            JSONArr = new JSONArray(json);
            String mensaje = "";

            String usuarioNom=usuario.getText().toString();
            String passId=pass.getText().toString();

            for (int i = 0; i < JSONArr.length(); i++) {
                JSONObject jsonObject = JSONArr.getJSONObject(i);
                if(usuarioNom.equals(jsonObject.optString("email")) && passId.equals(jsonObject.optString("codPersona"))){

                    valorRol=(jsonObject.optString("codRol"));

                    if (valorRol.equals("cli") )
                    {
                        Intent intent = new Intent(this, SolicitudSoporte.class);
                        intent.putExtra("codPersona", jsonObject.optString("codPersona"));
                        startActivity(intent);

                    }

                    if (valorRol.equals("admin") )
                    {
                        Intent intent = new Intent(this, SoportePendientes.class);
                        intent.putExtra("codPersona", jsonObject.optString("codPersona"));
                        startActivity(intent);

                    }


                }else{
                    Toast.makeText(getApplicationContext(), "Credenciales incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "url "+ e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "buffer "+e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "json "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }







    public void  llamafuncion(View v)
    {
        new Peticion();
    }

public  class Peticion extends AsyncTask<Void,Void,Void>
{

    @Override
    protected Void doInBackground(Void... voids) {

        final String url="http://apiandroid.netfly.net.ec/api/" ;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EditText valor_cedula = usuario;
        int  bandera = 0;

        ServiceUsuarios service = retrofit.create(ServiceUsuarios.class);
        Call<List<ResponseUsuarios>> response = service.getUsersGet();


        try {
            for(ResponseUsuarios user: response.execute().body()) {


                 Log.e("Respuesta:  ", user.getCodPersona() );


            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}

}
