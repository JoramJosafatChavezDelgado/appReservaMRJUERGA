package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLite_OpenHelper(Context context) {
        super(context, "DBjoram", null, 1);
    }
    //crear las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USUARIO_SQL_CREATE_TABLE);
        db.execSQL(PRODUCTOS_SQL_CREATE_TABLE);
    }
    //eliminar las tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(USUARIO_SQL_DELETE_ENTRIES);
        db.execSQL(PRODUCTOS_SQL_DELETE_ENTRIES);

        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // creando las columnas para las tablas
    public static class Columns implements BaseColumns {
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NOMUSU = "NomUsu";
        public static final String COLUMN_NAME_CORR = "Corr";
        public static final String COLUMN_NAME_PASSW = "Passw";
        public static final String COLUMN_NAME_PRECPROD = "PrecProd";
        public static final String COLUMN_NAME_NOMPROD = "NomProd";
        public static final String COLUMN_NAME_CANTPERS = "CantPers";
        public static final String COLUMN_NAME_IMGPROD = "ImgProd";
    }

    //CREADNO LAS TABLAS
    //tabla usuarios...............................................
    public static final String USUARIO_TABLE_NAME = "usuarios";
    private static final String USUARIO_SQL_CREATE_TABLE =
            "CREATE TABLE " + USUARIO_TABLE_NAME + " (" +
                    Columns._ID + " INTEGER PRIMARY KEY," +
                    Columns.COLUMN_NAME_NOMUSU + " TEXT, " +
                    Columns.COLUMN_NAME_CORR + " TEXT, " +
                    Columns.COLUMN_NAME_PASSW + " TEXT)";
    private static final String USUARIO_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + USUARIO_TABLE_NAME;
    //................................................................
    //tabla productos................................................
    public static final String PRODUCTOS_TABLE_NAME = "productos";
    private static final String PRODUCTOS_SQL_CREATE_TABLE =
            "CREATE TABLE " + PRODUCTOS_TABLE_NAME + " (" +
                    Columns._ID + " INTEGER PRIMARY KEY," +
                    Columns.COLUMN_NAME_NOMPROD + " TEXT, " +
                    Columns.COLUMN_NAME_IMGPROD + " TEXT, " +
                    Columns.COLUMN_NAME_PRECPROD + " REAL, " +
                    Columns.COLUMN_NAME_CANTPERS + " TEXT)";
    private static final String PRODUCTOS_SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PRODUCTOS_TABLE_NAME;
    //..................................................................

};

   /* @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table usuarios(_ID integer primary key autoincrement," +
                "Nombre text,Correo text,Password text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //metodo que me permita abrir la BD
        public void abrir()
        {
            this.getWritableDatabase();
        }

    // metodo permita cerrar la BD
        public void cerrar()
        {
            this.close();
        }
     //metodo que me permite insertar registros en la tabla usuarios
        public void insertarRegistro(String nom,String corr,String passw)
        {
            ContentValues valores= new ContentValues ();
            //comando put para agregar valores
            valores.put("Nombre",nom);
            valores.put("Correo",corr);
            valores.put("Password",passw);
            this.getWritableDatabase().insert("usuarios",null,valores);
        }
        //metodo que permite validar si el usuario exixte
        public Cursor ConsultarUsoPassw(String usu,String passw) throws SQLException
        {
            Cursor mcursor=null;
            //realiza la consulta.....................
                mcursor=this.getReadableDatabase().query("usuarios",new String[]{
                        "_ID","Nombre","Correo","Password"},
                        "Correo like'"+usu+"' and Password like '"+passw+"'",
                        null,null,null,null);
                //.......................................................................
            return mcursor;
        }
}
*/