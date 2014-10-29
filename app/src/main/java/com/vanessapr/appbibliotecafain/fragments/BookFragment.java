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
    public static final String EXTRA_BOOK = BookFragment.class.getName() + ".EXTRA_BOOK";
    public static final String EXTRA_POSITION_BOOK = BookFragment.class.getName() + ".EXTRA_POSITION_BOOK";
    private Libro mCurrentPosition = null;

    public BookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView...");

        if(savedInstanceState != null)
            mCurrentPosition = (Libro) savedInstanceState.getSerializable(EXTRA_POSITION_BOOK);

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");
        if(mCurrentPosition != null) {
            displayBookSingle(mCurrentPosition);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState...");
        // Save the current book selection in case we need to recreate the fragment
        outState.putSerializable(EXTRA_POSITION_BOOK, mCurrentPosition);
    }

    public void displayBookSingle(Libro libro) {
        Log.i(TAG, "my activity: " + getActivity());

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

        mCurrentPosition = libro;

    }


}
