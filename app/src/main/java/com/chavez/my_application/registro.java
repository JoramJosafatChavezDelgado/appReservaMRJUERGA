package com.chavez.my_application;

import OpenHelper.SQLite_OpenHelper;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends AppCompatActivity {





    Button btnGrabarUsu;
    EditText txtNomUsu,txtCorrUsu,txtPasswUsu;

    /*SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BDjoram",null,1);*/
    usuarios xx=new usuarios();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //enlazar los controles fisicos
        btnGrabarUsu=(Button)findViewById(R.id.btnRegUsu);
        txtNomUsu=(EditText)findViewById(R.id.txtNomUsu);
        txtCorrUsu=(EditText)findViewById(R.id.txtCorrUsu);
        txtPasswUsu=(EditText)findViewById(R.id.txtPassUsu);
        //implementar el evento click para grabar
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xx.insertarRegistro(registro.this,String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtCorrUsu.getText()),
                        String.valueOf(txtPasswUsu.getText()));

                Toast.makeText(getApplicationContext(),"Registro Almacenado con Exito",
                        Toast.LENGTH_LONG).show();
                //regresamos a la actividad
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
