package net.ivanvega.sqliteenandroid;

import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import net.ivanvega.sqliteenandroid.db.DAOUsuarios;
import net.ivanvega.sqliteenandroid.db.MiAdaptadorUsuariosConexion;
import net.ivanvega.sqliteenandroid.db.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtN,txtT,txtE,txtB,txtF;
    ListView lsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtN = findViewById(R.id.txtNombre);
        txtT = findViewById(R.id.txtTelefono);
        txtE = findViewById(R.id.txtEmail);
        txtB = findViewById(R.id.txtBuscar);
        txtF = findViewById(R.id.txtFecha);

         lsv = findViewById(R.id.lsv);
         lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 final int index = i;
                 Snackbar.make(view,"¿Estás seguro?", Snackbar.LENGTH_LONG).setAction("SI", new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         //vectorLibros.remove(id);
                         DAOUsuarios objDAO = new DAOUsuarios(getApplicationContext());
                         List<Usuario> lista = objDAO.getAll();
                         objDAO.delete(lista.get(index).getId());
                         btnCargar_click(view);
                     }
                 }).show();


             }
         });

         txtB.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                btnCargar_click(null, txtB.getText().toString());

            }
        });

    }

    public void btnI_click(View v){
        DAOUsuarios ado =
                new DAOUsuarios(getApplicationContext());

        long result = ado.add(
                new Usuario(
                    0, txtN.getText().toString(), txtT.getText().toString(),
                        txtE.getText().toString(), "@"+txtN.getText().toString(),
                        txtB.getText().toString()
                )
        );

        if (result>0){
            Toast.makeText(this, "Adición exitosa",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void btnCargar_click(View v){
        DAOUsuarios dao = new DAOUsuarios(this);
        List<Usuario> lst =  dao.getAll();
        for (Usuario item: lst
             ) {
            Log.d("USUARIO: " , String.valueOf( item.getId()));
            Log.d("USUARIO: " , item.getNombre());
        }
        Cursor c =  dao.getAllC();

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
          this, android.R.layout.simple_list_item_2 ,
          c , MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
          new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.NO_SELECTION

        );

        lsv.setAdapter(adp);
    }

    public void btnCargar_click(View v, String nombre){
        DAOUsuarios dao = new DAOUsuarios(this);
        List<Usuario> lst =  dao.getAll(nombre);
        for (Usuario item: lst
                ) {
            Log.d("USUARIO: " ,  String.valueOf( item.getId()));
            Log.d("USUARIO: " , item.getNombre());
        }

        Cursor c =  dao.getAllC(nombre);

        SimpleCursorAdapter adp = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_2 ,
                c , MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.NO_SELECTION

        );

        lsv.setAdapter(adp);
    }

}
