package com.uisrael.soporteexpress;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class servicioRegistro extends AsyncTask<Void,Void,String> {


private Context httContext;
ProgressDialog progressDialog;
public String resultadoapi="";
public String linkrequestAPI="";

public String codPersona="";
public String nombres="";
public String apellidos="";
public String email="";
public String telefono="";
public String codRol="";


public servicioRegistro(Context ctx,String linkAPI,String codPersona,String nombres,String apellidos,String email,String telefono,String codRol)
{
    this.httContext=ctx;
    this.linkrequestAPI=linkAPI;

    this.codPersona=codPersona;
    this.nombres=nombres;
    this.apellidos=apellidos;
    this.email=email;
    this.telefono=telefono;
    this.codRol=codRol;

}

@Override
    protected void onPreExecute(){

    super.onPreExecute();
    progressDialog=ProgressDialog.show(httContext, "Procesando Solicitud","Por favor, espere");

}

    @Override
    protected String doInBackground(Void... params) {
        String result= null;

        String wsURL = linkrequestAPI;
        URL url = null;
        try {
            // se crea la conexion al api
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //crear el objeto json para enviar por POST
            JSONObject parametrosPost= new JSONObject();
            parametrosPost.put("codPersona",codPersona);
            parametrosPost.put("nombres",nombres);
            parametrosPost.put("apellidos",apellidos);
            parametrosPost.put("email",email);
            parametrosPost.put("telefono",telefono);
            parametrosPost.put("codRol",codRol);



            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTENER EL RESULTADO DEL REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode=urlConnection.getResponseCode();// conexion OK?
            if(responseCode== HttpURLConnection.HTTP_OK){
                BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer sb= new StringBuffer("");
                String linea="";
                while ((linea=in.readLine())!= null){
                    sb.append(linea);
                    break;

                }
                in.close();
                result= sb.toString();
            }
            else{
                result= new String("Error: "+ responseCode);


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  result;

    }




    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoapi=s;
        Toast.makeText(httContext,resultadoapi,Toast.LENGTH_LONG).show();//mostrara una notificacion con el resultado del request

    }


    //FUNCIONES----------------------------------------------------------------------
    //Transformar JSON Obejct a String *******************************************
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }





}
