package com.example.pruebacrudsamuelsolis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {

    SQLiteDatabase bd;
    ArrayList<Usuario> lista = new ArrayList<Usuario>();
    Usuario u;
    Context ct;
    String nombreBD = "BDUsuarios";

    String tabla = "create table if not exists usuario(id integer primary key autoincrement, medico text, nombre text, edad text, peso text, descripcion text)";

    public daoUsuario(Context u) {
        this.ct = u;
        bd = u.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Usuario u) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("medico", u.getMedico());
        contenedor.put("nombre", u.getNombre());
        contenedor.put("edad", u.getEdad());
        contenedor.put("peso", u.getPeso());
        contenedor.put("descripcion", u.getDescripcion());
        return (bd.insert("usuario", null, contenedor)) > 0;
    }

    public boolean eliminar(int id) {
        return (bd.delete("usuario", "id=" + id, null)) > 0;
    }

    public boolean editar(Usuario u) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("medico", u.getMedico());
        contenedor.put("nombre", u.getNombre());
        contenedor.put("edad", u.getEdad());
        contenedor.put("peso", u.getPeso());
        contenedor.put("descripcion", u.getDescripcion());
        return (bd.update("usuario", contenedor, "id=" + u.getId(), null)) > 0;
    }
    public  ArrayList<Usuario>verTodo(){
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from usuario", null);
        if (cursor!=null&& cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                lista.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5) ));
            }while (cursor.moveToNext());
        }
        return lista;
    }
    public Usuario verUno(int posicion){
        Cursor cursor = bd.rawQuery("select * from usuario", null);
        cursor.moveToPosition(posicion);
        u=new Usuario(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3),cursor.getString(4),
                cursor.getString(5));
        return u;
    }
}