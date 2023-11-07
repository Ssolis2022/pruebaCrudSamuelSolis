package com.example.pruebacrudsamuelsolis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Usuario> lista;
    daoUsuario dao;
    Usuario u;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Usuario> lista, daoUsuario dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int getCount() {

        return lista.size();
    }

    @Override
    public Object getItem(int i) {

        u=lista.get(i);
        return null;
    }

    public long getItemId(int i) {
        u = lista.get(i);
        return u.getId();
    }

    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item,null);
        }
        u = lista.get(posicion);

        TextView medico = v.findViewById(R.id.medico);
        TextView nombre = v.findViewById(R.id.nombre);
        TextView edad = v.findViewById(R.id.edad);
        TextView peso = v.findViewById(R.id.peso);
        TextView descripcion = v.findViewById(R.id.descripcion);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);
        medico.setText(u.getMedico());
        nombre.setText(u.getNombre());
        edad.setText(u.getEdad());
        peso.setText(u.getPeso());
        descripcion.setText(u.getDescripcion());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText medico = dialog.findViewById(R.id.et_medico);
                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText edad = dialog.findViewById(R.id.et_edad);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText descripcion = dialog.findViewById(R.id.et_descripcion);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                u = lista.get(pos);
                setId(u.getId());
                medico.setText(u.getMedico());
                nombre.setText(u.getNombre());
                edad.setText(u.getEdad());
                peso.setText(u.getPeso());
                descripcion.setText(u.getDescripcion());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            u = new Usuario(getId(), medico.getText().toString(),
                                    nombre.getText().toString(),
                                    edad.getText().toString(),
                                    peso.getText().toString(),
                                    descripcion.getText().toString());
                            dao.editar(u);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "error", Toast.LENGTH_SHORT).show();
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                u = lista.get(posicion);
                setId(u.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }

                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
