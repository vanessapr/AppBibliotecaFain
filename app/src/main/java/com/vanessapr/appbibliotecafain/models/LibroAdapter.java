package com.vanessapr.appbibliotecafain.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vanessapr.appbibliotecafain.R;

import java.util.ArrayList;

/**
 * Created by Milagros on 27/10/2014.
 */
public class LibroAdapter extends ArrayAdapter<Libro> {

    Context mContext;
    private ArrayList<Libro> libros;

    public LibroAdapter(Context context, ArrayList<Libro> libros) {
        super(context, R.layout.books_item);

        this.mContext = context;
        this.libros = libros;
    }

    @Override
    public int getCount() {
        return libros.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceHolder placeHolder;

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.books_item, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
        } else {
            placeHolder = (PlaceHolder) convertView.getTag();
        }


        placeHolder.titulo.setText(libros.get(position).getTitulo());
        placeHolder.autor.setText(libros.get(position).getAutor());
        placeHolder.contenido.setText(libros.get(position).getResumen());
        placeHolder.fecha.setText(libros.get(position).getAnio());

        return (convertView);
    }

    private static class PlaceHolder {
        TextView titulo;
        TextView autor;
        TextView contenido;
        TextView fecha;

        public static PlaceHolder generate(View convertView) {
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.titulo = (TextView) convertView.findViewById(R.id.tvBookTitulo);
            placeHolder.autor = (TextView) convertView.findViewById(R.id.tvBookAutor);
            placeHolder.contenido = (TextView) convertView.findViewById(R.id.tvBookContenido);
            placeHolder.fecha = (TextView) convertView.findViewById(R.id.tvBookFecha);
            return placeHolder;
        }
    }
}
