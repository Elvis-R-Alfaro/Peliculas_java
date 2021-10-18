/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodesno.peliculas;

import com.rodesno.utilities.Layout;
import java.util.Scanner;
import java.util.ArrayList;
import com.rodesno.dao.PeliculasDB;

/**
 *
 * @author EAlfa
 */
public class Aplication {
    private Scanner _EntradaTeclado;
    private ArrayList _MisPeliculas;
    private Integer _MiPeliculaIdCounter;
    private PeliculasDB _PeliculasModel;
    public Aplication(Scanner EntradaTeclado){
        this._EntradaTeclado = EntradaTeclado;
        this._MisPeliculas = new ArrayList<PeliculaItem>();
        this._MiPeliculaIdCounter = 0;
        this._PeliculasModel  = new PeliculasDB();
        this._PeliculasModel.TableInitialize();
        this._MisPeliculas = this._PeliculasModel.getPeliculaItems(true);
    }
    
    public void activarEvento(String opcionMenu){
        switch(opcionMenu.toUpperCase()){
            case "M":
                this.mostrarRegistros();
                break;  
            case "I":
                System.out.println("Ingresar Registro");
                this.ingresarNuevoRegistro();
                break;
            case "A":
                System.out.println("Actualizar Registro");
                this.actualizarRegistro();
                break;
            case "E":
                System.out.println("Eliminar Registro");
                this.eliminarRegistro();
                break;
            case "S":
                break;
            default:
                System.out.println("Opcion no reconocida!");
                break;
        }
    }
    
    private void ingresarNuevoRegistro(){
        Layout.printHeader("Nuevo Registro de Pelicula");
        PeliculaItem nuevaPeliculaItem = new PeliculaItem();
        nuevaPeliculaItem.setTitulo(Layout.obtenerValorparaCampo("Titulo", "Titulo Z", this._EntradaTeclado));
        nuevaPeliculaItem.setDescripcion(Layout.obtenerValorparaCampo("Descripcion", "Descripcion X", this._EntradaTeclado));
        nuevaPeliculaItem.setCalificacion(Integer.valueOf(Layout.obtenerValorparaCampo("Calificacion /5", "4", this._EntradaTeclado)));
        nuevaPeliculaItem.setDuracion(Integer.valueOf(Layout.obtenerValorparaCampo("Duracion mins", "100", this._EntradaTeclado)));
        nuevaPeliculaItem.setAnioLanzamiento(Integer.valueOf(Layout.obtenerValorparaCampo("Anio Lanzamiento", "2021", this._EntradaTeclado)));
        
        
        this._PeliculasModel.insertPeliculaItem(nuevaPeliculaItem);
        this._MisPeliculas = this._PeliculasModel.getPeliculaItems(true);
        
        Layout.printSeparator();
        System.out.println("Registro insertado exitosamente!");
    }
    
    private void mostrarRegistros(){
        Layout.printSeparator();
        ArrayList<String> columnas = new ArrayList<String>();
        columnas.add("Codigo");
        columnas.add("Titulo");
        columnas.add("Descripcion");
        columnas.add("Calificacion");
        columnas.add("Duracion");
        columnas.add("Lanzamiento");
        
        ArrayList<Integer> anchos = new ArrayList<Integer>();
        anchos.add(6);
        anchos.add(22);
        anchos.add(21);
        anchos.add(9);
        anchos.add(8);
        anchos.add(9);
        
        
        
        try{
            //Print header
            Layout.imprimirEnColumna(columnas, anchos,"|");
            Layout.printSeparator();
            for (int i = 0; i < this._MisPeliculas.size(); i++) {
                columnas = ((PeliculaItem) this._MisPeliculas.get(i)).obtenerCampos();
                Layout.imprimirEnColumna(columnas, anchos, "|");
            }
        }catch(Exception ex){
            System.err.println("Error " + ex.getMessage());
        }
    }
    
    private void actualizarRegistro(){
        Layout.printHeader("Actualizar Registro");
        Integer selectedId = Integer.valueOf(Layout.obtenerValorparaCampo("Ingrese Codigo de Registro", "0", this._EntradaTeclado));
        PeliculaItem selectedPeliculaItem = null;
        selectedPeliculaItem = this._PeliculasModel.getPeliculatemById(selectedId);
        if (selectedPeliculaItem == null) {
            System.out.println("Registro solicitado no existe!");
        }else{
            selectedPeliculaItem.setTitulo(Layout.obtenerValorparaCampo("Titulo", selectedPeliculaItem.getTitulo(), this._EntradaTeclado));
            selectedPeliculaItem.setDescripcion(Layout.obtenerValorparaCampo("Descripcion", selectedPeliculaItem.getDescripcion(), this._EntradaTeclado));
            selectedPeliculaItem.setCalificacion(Integer.valueOf(Layout.obtenerValorparaCampo("Calificacion /5", String.valueOf(selectedPeliculaItem.getCalificacion()), this._EntradaTeclado)));
            selectedPeliculaItem.setDuracion(Integer.valueOf(Layout.obtenerValorparaCampo("Duracion mins", String.valueOf(selectedPeliculaItem.getDuracion()), this._EntradaTeclado)));
            selectedPeliculaItem.setAnioLanzamiento(Integer.valueOf(Layout.obtenerValorparaCampo("Anio Lanzamiento", String.valueOf(selectedPeliculaItem.getAnioLanzamiento()), this._EntradaTeclado)));
            
            this._PeliculasModel.updatePeliculaItem(selectedPeliculaItem);
            this._MisPeliculas = this._PeliculasModel.getPeliculaItems(true);
            Layout.printSeparator();
            System.out.println("Registro actualizado exitosamente!");
        }

    }
    
    private void eliminarRegistro(){
        Layout.printHeader("Eliminar Registro");
        Integer selectedId = Integer.valueOf(Layout.obtenerValorparaCampo("Ingrese Codigo de Registro", "0", this._EntradaTeclado));
        
        PeliculaItem selectedPeliculaItem = this._PeliculasModel.getPeliculatemById(selectedId);
        if (selectedPeliculaItem != null) {
            Layout.printSeparator();
            String confirmado = Layout.obtenerValorparaCampo("Desea eliminar este registro? S - N", "N", this._EntradaTeclado);
            if (confirmado.toUpperCase().equals("S")) {
                
                this._PeliculasModel.deletePeliculaItem(selectedPeliculaItem);
                this._MisPeliculas = this._PeliculasModel.getPeliculaItems(true);
                Layout.printSeparator();
                System.out.println("Registro eliminado exitosamente!");
            }
        }else{
            System.out.println("Registro solicitado no existe!");
        }
    }
}
