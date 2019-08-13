package com.example.servicioswebandroidphp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {
    EditText usr,nombre,correo,clave;
    Button registrarr,iniciarsesionr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        usr=findViewById(R.id.etusrr);
        nombre=findViewById(R.id.etnombrer);
        correo=findViewById(R.id.etcorreor);
        clave=findViewById(R.id.etclaver);
        registrarr=findViewById(R.id.btnregistrarr);
        iniciarsesionr=findViewById(R.id.btniniciarsesionr);
        registrarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroUsuario("http://172.16.22.4:1018/ServiciosWebAndroidPHP/registro.php");
            }
        });
    }

            private void RegistroUsuario(String s) {
                //se crea un objeto para solicitud http
                StringRequest stringRequest=new StringRequest(Request.Method.POST, s, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Registro de usuario exitoso",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    //el siguiente codigo pasa en formato de JSON a la base de datos (tabla usuario)
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parametros=new HashMap<String, String>();
                        parametros.put("usr",usr.getText().toString());
                        parametros.put("clave",clave.getText().toString());
                        parametros.put("nombre",nombre.getText().toString());
                        parametros.put("correo",correo.getText().toString());
                        return parametros;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
    }
}
