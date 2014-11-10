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

        if (where != null && !where.equals("")) {
            sql.append(" WHERE ").append(where);
        }

        if (order != null && !order.equals("")){
            sql.append(" ORDER BY ").append(order);
        }

        Log.d(TAG, "getLibros: " + sql.toString());

        cursor = database.executeQuery(sql.toString());
        int filas = cursor.getCount();
        for(int i=1; i <= filas; i++) {
            Libro book = new Libro();

            book.setId(cursor.getInt(0));
            book.setCodigo(cursor.getString(1));
            book.setAutor(cursor.getString(2));
            book.setTitulo(cursor.getString(3));
            book.setLugar(cursor.getString(4));
            book.setFecha(cursor.getString(5));
            book.setEditorial(cursor.getString(6));
            book.setPaginas(cursor.getString(7));
            book.setContenidos(cursor.getString(8));
            book.setDescriptores(cursor.getString(9));
            book.setUrlPdf(cursor.getString(10));
            book.setTipo(cursor.getInt(11));
            libros.add(book);
            cursor.moveToNext();
        }

        cursor.close();
        database.close();

        return libros;
    }

}
