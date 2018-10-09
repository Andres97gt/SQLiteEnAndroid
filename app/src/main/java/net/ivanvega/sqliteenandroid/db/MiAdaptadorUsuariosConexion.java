package net.ivanvega.sqliteenandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiAdaptadorUsuariosConexion extends SQLiteOpenHelper {

    public static final String [] COLUMNS_USUARIOS = {"_id", "nombre", "telefono", "email", "red_social"  };
    public static final String [] TABLES_DB = {"usuarios"};

    private String SCRIPT_DB  = "create table Usuarios (_id integer primary key autoincrement, " +
            "nombre text not null, telefono text, email text, red_social text );";


    public MiAdaptadorUsuariosConexion (Context ctx){
        super(ctx, "midb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
