package com.vanessapr.appbibliotecafain.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.vanessapr.appbibliotecafain.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Milagros on 27/10/2014.
 */
public class LibroModel {
    private static final String TAG = "LibroModel";
    private static final String TABLE_NAME = "libros";
    private DatabaseHelper database;
    private Cursor cursor;

    public LibroModel(Context context) {
        database = new DatabaseHelper(context);
    }

    public ArrayList<Libro> getLibros(String where, String order) {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);

        if (where != null) {
            sql.append(" WHERE ").append(where);
            //sql += " WHERE " + where;
        }

        if (order != null){
            sql.append(" ORDER BY ").append(order);
            //sql += " ORDER BY " + order;
        }

        Log.d(TAG, "getLibros: " + sql.toString());

        cursor = database.executeQuery(sql.toString());
        int filas = cursor.getCount();
        for(int i=1; i <= filas; i++) {
            Libro book = new Libro();

            book.setId(cursor.getInt(0));
            book.setCodigo(cursor.getString(1));

            if ( cursor.getString(2) != null )
                book.setAutor(cursor.getString(2));
            else if( cursor.getString(3) != null)
                book.setAutor(cursor.getString(3));

            if( cursor.getString(4) != null)
                book.setTitulo(cursor.getString(4));
            else
                book.setTitulo(cursor.getString(14));

            book.setPaginas(cursor.getString(5));
            book.setVolumen(cursor.getString(6));
            book.setEditorial(cursor.getString(7));
            book.setCiudad(cursor.getString(8));
            book.setEdicion(cursor.getString(9));
            book.setAnio(cursor.getString(10));
            book.setResumen(cursor.getString(11));
            book.setDescriptores(cursor.getString(12));
            book.setEditorInstitucional(cursor.getString(13));
            //book.setTituloRevista(cursor.getString(14));
            book.setVolRevista(cursor.getString(15));
            book.setFasciculo(cursor.getString(16));
            book.setAsesor(cursor.getString(17));
            book.setGrado(cursor.getString(18));

            libros.add(book);
            cursor.moveToNext();
        }

        cursor.close();
        database.close();

        return libros;
    }

}
