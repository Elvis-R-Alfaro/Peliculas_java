/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodesno.peliculas;

import java.util.ArrayList;
/**
 *
 * @author EAlfa
 */
public class PeliculaItem {
    /**
     * @return the _id
     */
    public int getId() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void setId(int _id) {
        this._id = _id;
    }

    /**
     * @return the _titulo
     */
    public String getTitulo() {
        return _titulo;
    }

    /**
     * @param _titulo the _titulo to set
     */
    public void setTitulo(String _titulo) {
        this._titulo = _titulo;
    }

    /**
     * @return the _descripcion
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * @param _descripcion the _descripcion to set
     */
    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    /**
     * @return the _calificacion
     */
    public int getCalificacion() {
        return _calificacion;
    }

    /**
     * @param _calificacion the _calificacion to set
     */
    public void setCalificacion(int _calificacion) {
        this._calificacion = _calificacion;
    }

    /**
     * @return the _duracion
     */
    public int getDuracion() {
        return _duracion;
    }

    /**
     * @param _duracion the _duracion to set
     */
    public void setDuracion(int _duracion) {
        this._duracion = _duracion;
    }
    
    /**
     * @return the _anioLanzamiento
     */
    public int getAnioLanzamiento() {
        return _anioLanzamiento;
    }

    /**
     * @param _anioLanzamiento the _anioLanzamiento to set
     */
    public void setAnioLanzamiento(int _anioLanzamiento) {
        this._anioLanzamiento = _anioLanzamiento;
    }
    
    private int _id;
    private String _titulo;
    private String _descripcion;
    private int _calificacion;
    private int _duracion;
    private int _anioLanzamiento;
    
    public PeliculaItem(){
        this._id = 0;
        this._titulo = "";
        this._descripcion = "";
        this._duracion = 0;
        this._anioLanzamiento = 0;
        this._calificacion = 0;
    }
    
    public PeliculaItem(int id, String titulo, String descripcion,  int calificacion, int duracion, int anioLanzamiento){
        this._id = id;
        this._titulo = titulo;
        this._descripcion = descripcion;
        this._calificacion = calificacion;
        this._duracion = duracion;
        this._anioLanzamiento = anioLanzamiento;
    }
   //Setter Getters 
    
    public ArrayList<String> obtenerCampos(){
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(String.valueOf(_id));
        campos.add(_titulo);
        campos.add(_descripcion);
        campos.add(String.valueOf(_calificacion));
        campos.add(String.valueOf(_duracion));
        campos.add(String.valueOf(_anioLanzamiento));
        
        return campos;
    }
}
