package com.vanessapr.appbibliotecafain.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private static final String TAG = "BookFragment";
    public static final String EXTRA_BOOK = BookFragment.class.getName() + "EXTRA_BOOK";
    private Activity mActivity;

    public BookFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach...");
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        return view;
    }

    public void displayBookSingle(Libro libro) {
        System.out.println("entrando....: ");
        System.out.println("titulo: " + mActivity);

        TextView tvTitulo = (TextView) getActivity().findViewById(R.id.tv_book_titulo);


        TextView tvCodigo = (TextView) getActivity().findViewById(R.id.tv_book_codigo);
        TextView tvAutor = (TextView) getActivity().findViewById(R.id.tv_book_autor);
        TextView tvEditorial = (TextView) getActivity().findViewById(R.id.tv_book_editorial);
        TextView tvCiudad = (TextView) getActivity().findViewById(R.id.tv_book_ciudad);
        TextView tvAnio = (TextView) getActivity().findViewById(R.id.tv_book_anio);
        TextView tvContenido = (TextView) getActivity().findViewById(R.id.tv_book_contenido);
        TextView tvPaginas = (TextView) getActivity().findViewById(R.id.tv_book_paginas);


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
