package com.chavez.my_application;



import OpenHelper.SQLite_OpenHelper;
import adapters.UsuariosAdapters;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class usuarios extends AppCompatActivity {
    ListView listViewCredentials;
    ArrayList<usuarios> channelItems;
    UsuariosAdapters channelAdapter;
        private String nombre;
        private String correo;

        private int id;
        private String password;
        public static SQLite_OpenHelper dbInstance;

    public SQLite_OpenHelper getDbInstance(Context _context) {
        if ( usuarios.dbInstance == null ) {
            usuarios.dbInstance = new SQLite_OpenHelper(_context);
            return usuarios.dbInstance;
        }
        return usuarios.dbInstance;
    }

    /* LocalStorage */




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);

        listViewCredentials = (ListView) findViewById(R.id.lvUsuarios);
usuarios uu =new usuarios();
        channelItems = uu.getAll(this);

        channelAdapter =
                new UsuariosAdapters(this, channelItems);

        listViewCredentials.setAdapter(channelAdapter);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //metodo que me permite insertar registros en la tabla usuarios
    public void insertarRegistro(Context _context, String nom,String corr,String passw)

    {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        ContentValues valores= new ContentValues ();
        //comando put para agregar valores
        valores.put(SQLite_OpenHelper.Columns.COLUMN_NAME_CORR,corr);
        valores.put(SQLite_OpenHelper.Columns.COLUMN_NAME_PASSW,passw);
        db.insert("usuarios",null,valores);
    }
    //metodo que permite validar si el usuario exixte
    public Cursor ConsultarUsoPassw(Context _context,String usu,String passw) throws SQLException
    {
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        Cursor mcursor=null;
        //realiza la consulta.....................
        mcursor=db.query("usuarios",new String[]{
                        "_ID",SQLite_OpenHelper.Columns.COLUMN_NAME_CORR,
                        SQLite_OpenHelper.Columns.COLUMN_NAME_PASSW},
                "Corr like'"+usu+"' and Passw like '"+passw+"'",
                null,null,null,null);
        //.......................................................................
        return mcursor;
    }
//---------------------------------------------------------------------------------------------
    public ArrayList<usuarios> getAll(Context _context) {
        ArrayList<usuarios> rows = new ArrayList<>();
        SQLiteDatabase db = this.getDbInstance(_context).getReadableDatabase();

        String[] fields = new String[] {SQLite_OpenHelper.Columns._ID,
                SQLite_OpenHelper.Columns.COLUMN_NAME_CORR,
                SQLite_OpenHelper.Columns.COLUMN_NAME_PASSW};
        String[] args = new String[] {this.getId() + ""};

        Cursor c = db.query(SQLite_OpenHelper.USUARIO_TABLE_NAME, fields,
                null, null,  null, null,
                SQLite_OpenHelper.Columns._ID+" DESC");

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            do {
                usuarios n = new usuarios();
                n.setId(c.getInt(0));
                n.setNombre(c.getString(1));
                n.setPassword(c.getString(2));
                rows.add(n);
            } while(c.moveToNext());
        }
        return rows;
    }
    //-------------------------------------------------------------------------------------
}

