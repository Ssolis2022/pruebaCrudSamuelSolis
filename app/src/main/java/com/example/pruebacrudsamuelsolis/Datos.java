package com.example.pruebacrudsamuelsolis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Datos extends AppCompatActivity {

    daoUsuario dao;
    Adaptador adapter;
    ArrayList<Usuario> lista;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        dao = new daoUsuario(Datos.this);
        lista = dao.verTodo();
        adapter = new Adaptador(this, lista, dao);
        ListView list = findViewById(R.id.lista);
        Button insertar = findViewById(R.id.button);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Datos.this);
                dialog.setTitle("Nuevo registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText medico = dialog.findViewById(R.id.et_medico);
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText edad = dialog.findViewById(R.id.et_edad);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText descripcion = dialog.findViewById(R.id.et_descripcion);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            u = new Usuario(medico.getText().toString(),
                                    nombre.getText().toString(),
                                    edad.getText().toString(),
                                    peso.getText().toString(),
                                    descripcion.getText().toString());
                            dao.insertar(u);
                            lista = dao.verTodo();
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }
}
