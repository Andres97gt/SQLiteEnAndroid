package net.ivanvega.sqliteenandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios {

    private SQLiteDatabase _ad ;

    public DAOUsuarios(Context ctx) {
          _ad = new MiAdaptadorUsuariosConexion(ctx).getWritableDatabase();
    }

    public long add(Usuario u) {

        ContentValues cv = new ContentValues();

        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[1], u.getNombre());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[2], u.getTelefono());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[3], u.getEmail());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[4], u.getRed_social());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[5], u.getFecha());

        return _ad.insert(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                null,
                cv);

    }

    public void delete(int id) {

        _ad.delete(MiAdaptadorUsuariosConexion.TABLES_DB[0],"_id = ?",new String[]{String.valueOf(id+"")});

    }

    public List<Usuario > getAll(){
        List <Usuario> lst = new ArrayList<Usuario>();

         Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null,null,null,null,null
        );

         while(c.moveToNext()){

             lst.add(
               new Usuario(c.getInt(0), c.getString(1),
                       c.getString(2), c.getString(3),
                       c.getString(4), c.getString(5) )
             );

         }


        return lst;
    }

    public List<Usuario> getAll(String nombre){
        List <Usuario> lst = new ArrayList<Usuario>();

        Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where nombre LIKE \"%" + nombre + "%\"", null);

        while(c.moveToNext()){

            lst.add(
                    new Usuario(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3),
                            c.getString(4), c.getString(5))
            );

        }
        return lst;
    }

    public Cursor getAllC(){

        Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );

        return c;
    }

    public Cursor getAllC(String nombre){

        Cursor c = _ad.rawQuery("SELECT * FROM usuarios Where nombre LIKE \"%" + nombre + "%\"", null);
        return c;

    }
}