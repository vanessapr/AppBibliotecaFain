package com.vanessapr.appbibliotecafain.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanessapr.appbibliotecafain.R;
import com.vanessapr.appbibliotecafain.models.Libro;

/**
 * Created by Milagros on 27/10/2014.
 */
public class BookFragment extends Fragment {
    private TextView tvTitulo;
    private TextView tvCodigo;
    private TextView tvAutor;
    private TextView tvEditorial;
    private TextView tvCiudad;
    private TextView tvAnio;
    private TextView tvContenido;
    private TextView tvPaginas;

    public BookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        tvTitulo = (TextView) view.findViewById(R.id.tv_book_titulo);
        tvCodigo = (TextView) view.findViewById(R.id.tv_book_codigo);
        tvAutor = (TextView) view.findViewById(R.id.tv_book_autor);
        tvEditorial = (TextView) view.findViewById(R.id.tv_book_editorial);
        tvCiudad = (TextView) view.findViewById(R.id.tv_book_ciudad);
        tvAnio = (TextView) view.findViewById(R.id.tv_book_anio);
        tvContenido = (TextView) view.findViewById(R.id.tv_book_contenido);
        tvPaginas = (TextView) view.findViewById(R.id.tv_book_paginas);
        return view;
    }

    public void displayBookSingle(Libro libro) {
        tvTitulo.setText(libro.getTitulo());
        tvCodigo.setText(libro.getCodigo());
        tvAutor.setText(libro.getAutor());
        tvEditorial.setText(libro.getEditorial());
        tvCiudad.setText(libro.getCiudad());
        tvAnio.setText(libro.getAnio());
        tvContenido.setText(libro.getResumen());
        tvPaginas.setText(libro.getPaginas());
    }

}
