package com.vanessapr.appbibliotecafain.models;

import java.io.Serializable;

/**
 * Created by Milagros on 27/10/2014.
 */
public class Libro implements Serializable {
    private int id;
    private String codigo;
    private String autor;
    private String titulo;
    private String paginas;
    private String volumen;
    private String editorial;
    private String ciudad;
    private String edicion;
    private String anio;
    private String resumen;
    private String descriptores;
    private String editorInstitucional;
    private String volRevista;
    private String fasciculo;
    private String asesor;
    private String institucion;
    private String grado;

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
        return autor;
    }
    public void setAutor(String autorPersonal) {
        autor = autorPersonal;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String mTitulo) {
        titulo = mTitulo;
    }
    public String getPaginas() {
        return paginas;
    }
    public void setPaginas(String mPaginas) {
        paginas = mPaginas;
    }
    public String getVolumen() {
        return volumen;
    }
    public void setVolumen(String mVolumen) {
       volumen = mVolumen;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String mEditorial) {
        editorial = mEditorial;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String mCiudad) {
        ciudad = mCiudad;
    }
    public String getEdicion() {
        return edicion;
    }
    public void setEdicion(String mEdicion) {
        edicion = mEdicion;
    }
    public String getAnio() {
        return anio;
    }
    public void setAnio(String mAnio) {
        this.anio = mAnio;
    }
    public String getResumen() {
        return resumen;
    }
    public void setResumen(String mResumen) {
        resumen = mResumen;
    }
    public String getDescriptores() {
        return descriptores;
    }
    public void setDescriptores(String mDescriptores) {
        descriptores = mDescriptores;
    }
    public String getEditorInstitucional() {
        return editorInstitucional;
    }
    public void setEditorInstitucional(String mEditorInstitucional) {
        editorInstitucional = mEditorInstitucional;
    }
    public String getVolRevista() {
        return volRevista;
    }
    public void setVolRevista(String mVolRevista) {
        volRevista = mVolRevista;
    }
    public String getFasciculo() {
        return fasciculo;
    }
    public void setFasciculo(String mFasciculo) {
        fasciculo = mFasciculo;
    }
    public String getAsesor() {
        return asesor;
    }
    public void setAsesor(String mAsesor) {
        asesor = mAsesor;
    }
    public String getInstitucion() {
        return institucion;
    }
    public void setInstitucion(String mInstitucion) {
        institucion = mInstitucion;
    }
    public String getGrado() {
        return grado;
    }
    public void setGrado(String mGrado) {
        grado = mGrado;
    }

}
