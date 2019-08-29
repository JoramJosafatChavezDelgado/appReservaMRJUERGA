package com.chavez.my_application;

import OpenHelper.SQLite_OpenHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    usuarios xx=new usuarios();

    Button btnRegistro;
    Button btnIngresar;
    //instancia de la BD...............................................................................
    SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BDjoram",null,1);
    //..................................................................................................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistro= (Button) findViewById(R.id.btnRegistrese);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),registro.class);
                startActivity(i);
            }
        });

        btnIngresar= (Button) findViewById(R.id.btnIngreso);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtusu=(EditText)findViewById(R.id.txtCorrUsu);
                EditText txtpass=(EditText)findViewById(R.id.txtPassw);
            try {
                Cursor cursor=xx.ConsultarUsoPassw
                        (MainActivity.this , txtusu.getText().toString(),txtpass.getText().toString());
                if (cursor.getCount()>0)
                {
                    Intent i= new Intent(getApplicationContext(), usuarios.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuario y/o Contraseña incorrectos",
                            Toast.LENGTH_LONG).show();
                }
                //limpiar las cajas de texto
                txtusu.setText("");
                txtpass.setText("");
                txtusu.findFocus(); //foacliso el cursos en la caja usuario
            }
                catch (SQLException e){
                e.printStackTrace();
                }
            }
        });
    }
}
