package com.vanessapr.appbibliotecafain.models;

import java.io.Serializable;

/**
 * Created by Milagros on 27/10/2014.
 */
public class Libro implements Serializable {
    private int id;
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
}
