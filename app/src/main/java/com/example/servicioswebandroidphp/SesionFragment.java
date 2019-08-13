package com.example.servicioswebandroidphp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    //Instanciar los objetos para tomar los objetos en formato JSON
    RequestQueue rq; //cola de solicitud
    JsonRequest jrq; //objeto para JSON

    EditText usr,correo,nombre,clave;
    Button registrar,iniciar_sesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        usr = vista.findViewById(R.id.etusuario);
        clave = vista.findViewById(R.id.etclave);
        //nombre=vista.findViewById(R.id.etnombre);
        //correo=vista.findViewById(R.id.etcorreo);
        iniciar_sesion = vista.findViewById(R.id.btniniciar);
        registrar=vista.findViewById(R.id.btnregistrar);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Main3Activity.class);
                startActivity(intent);
            }
        });
        rq = Volley.newRequestQueue(getContext());//requerimiento Volley
        iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarses();
            }
        });
        return vista;
    }
    private void iniciarses() {
        String url = "http://172.16.22.4:1018/ServiciosWebAndroidPHP/sesion.php?usr="+usr.getText().toString()+"&clave="+clave.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        rq.add(jrq);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Usuario No encontrado...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Usuario encontrado...",Toast.LENGTH_SHORT).show();

        //Instanciar el objeto usua de la clase usuario

        usuario usua = new usuario();

        //Generar un arreglo de Json para recibir la informacion del array datos(JSON)

        JSONArray jsonusuario=response.optJSONArray("datos");

        //Crear un objeto del JSONObject para tomar el dato recibido del array jsonusuario

        JSONObject ousuario = null;

        try {
            ousuario= jsonusuario .getJSONObject(0);//posición 0 del arreglo....
            usua.setUsr(ousuario .optString("usr"));
            usua.setClave(ousuario .optString("clave"));
            usua.setNombre(ousuario .optString("nombre"));
            //usua.setCorreo(ousuario .optString("correo"));

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        Intent logueado=new Intent(getContext(),Main2Activity.class);
        logueado.putExtra("usr",usua.getUsr());
        logueado.putExtra("nombree",usua.getNombre());
        startActivity(logueado);

    }
}
