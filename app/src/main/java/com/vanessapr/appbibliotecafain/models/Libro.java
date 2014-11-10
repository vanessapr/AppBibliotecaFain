package com.vanessapr.appbibliotecafain.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanessapr.appbibliotecafain.R;

import java.text.DecimalFormat;

/**
 * Created by Milagros on 27/10/2014.
 */
public class Libro implements Parcelable {
    private int id;
    private int tipo; //1:books, 2:reviews, 3:CD, 4:others
    private int size;
    private String codigo;
    private String autor_libro;
    private String titulo;
    private String lugar;
    private String fecha;
    private String editorial;
    private String paginacion;
    private String contenidos;
    private String descriptores;
    private String url_pdf;

    /*
    * Constructs
    */
    public Libro() {

    }

    private Libro(Parcel in) {
        id = in.readInt();
        tipo = in.readInt();
        size = in.readInt();
        codigo = in.readString();
        autor_libro = in.readString();
        titulo = in.readString();
        lugar = in.readString();
        fecha = in.readString();
        editorial = in.readString();
        paginacion = in.readString();
        contenidos = in.readString();
        descriptores = in.readString();
        url_pdf = in.readString();
    }

    /*
     * Methods Gets and Sets
     */
    public int getId() {
        return id;
    }
    public void setId(int mId) {
        id = mId;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String mCodigo) {
        codigo = mCodigo;
    }
    public String getAutor() {
        return autor_libro;
    }
    public void setAutor(String autor) {
        autor_libro = autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String mTitulo) {
        titulo = mTitulo;
    }
    public String getPaginas() {
        return paginacion;
    }
    public void setPaginas(String paginas) {
        paginacion = paginas;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String mEditorial) {
        editorial = mEditorial;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String mLugar) { lugar = mLugar; }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String mFecha) {
        fecha = mFecha;
    }
    public String getContenidos() {
        return contenidos;
    }
    public void setContenidos(String mContenidos) {
        contenidos = mContenidos;
    }
    public String getDescriptores() {
        return descriptores;
    }
    public void setDescriptores(String mDescriptores) {
        descriptores = mDescriptores;
    }
    public String getUrlPdf() { return url_pdf; }
    public void setUrlPdf(String url) { url_pdf = url; }
    public int getTipo(){
        return tipo;
    }
    public void setTipo(int mTipo) {
        tipo = mTipo;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int mSize) {
        size = mSize;
    }

    /*
     * Methods necessary for implements Parcelable
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(tipo);
        out.writeInt(size);
        out.writeString(codigo);
        out.writeString(autor_libro);
        out.writeString(titulo);
        out.writeString(lugar);
        out.writeString(fecha);
        out.writeString(editorial);
        out.writeString(paginacion);
        out.writeString(contenidos);
        out.writeString(descriptores);
        out.writeString(url_pdf);
    }

    public static final Parcelable.Creator<Libro> CREATOR = new Parcelable.Creator<Libro>(){

        @Override
        public Libro createFromParcel(Parcel in) {
            return new Libro(in);
        }

        @Override
        public Libro[] newArray(int size) {
            return new Libro[size];
        }
    };

    /*
    * Views for types of books
     */
    public ViewGroup render(Context ctx, int type) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup view;

        if(type == 2) {
            view = (ViewGroup) inflater.inflate(R.layout.template_review, null, false);
        } else if(type == 3) {
            view = (ViewGroup) inflater.inflate(R.layout.template_multimedia, null, false);
        } else {
            view = (ViewGroup) inflater.inflate(R.layout.template_book, null, false);
        }

        if(view.findViewById(R.id.tv_book_codigo) != null) {
            TextView tvCodigo = (TextView) view.findViewById(R.id.tv_book_codigo);
            tvCodigo.setText(codigo);
        }

        if(view.findViewById(R.id.tv_book_autor) != null) {
            TextView tvAutor = (TextView) view.findViewById(R.id.tv_book_autor);
            tvAutor.setText(autor_libro);
        }

        if(view.findViewById(R.id.tv_book_temas) != null) {
            TextView tvTemas = (TextView) view.findViewById(R.id.tv_book_temas);
            tvTemas.setText(descriptores);
        }

        if(view.findViewById(R.id.tv_book_size) != null) {
            TextView tvSize = (TextView) view.findViewById(R.id.tv_book_size);
            // calculate size in MB
            StringBuilder cSize = new StringBuilder("Pesa ");
            if(size > 1024){
                float newsize = (float) size/1024;
                DecimalFormat df = new DecimalFormat("0.00");
                cSize.append(df.format(newsize)).append(" MB");
            } else {
                cSize.append(String.valueOf(size)).append(" KB");
            }

            tvSize.setText(cSize.toString());
        }


        TextView tvTitulo = (TextView) view.findViewById(R.id.tv_book_titulo);
        TextView tvEditorial = (TextView) view.findViewById(R.id.tv_book_editorial);
        TextView tvCiudad = (TextView) view.findViewById(R.id.tv_book_ciudad);
        TextView tvAnio = (TextView) view.findViewById(R.id.tv_book_anio);
        TextView tvContenido = (TextView) view.findViewById(R.id.tv_book_contenido);
        TextView tvPaginas = (TextView) view.findViewById(R.id.tv_book_paginas);

        tvTitulo.setText(titulo);
        tvEditorial.setText(editorial);
        tvCiudad.setText(lugar);
        tvAnio.setText(fecha);
        tvContenido.setText(contenidos);
        tvPaginas.setText(paginacion);

        return view;
    }

}
