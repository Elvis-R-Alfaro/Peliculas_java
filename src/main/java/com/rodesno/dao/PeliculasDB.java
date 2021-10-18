/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodesno.dao;

import com.rodesno.peliculas.PeliculaItem;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author EAlfa
 */
public class PeliculasDB {
    private ArrayList<PeliculaItem> _PeliculasItems = null;
    
    public PeliculasDB(){
        this._PeliculasItems = new ArrayList<PeliculaItem>();
    }
    
    public ArrayList<PeliculaItem> getPeliculaItems (){
       return this.getPeliculaItems(false);
    }
    
    public void TableInitialize(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS PELICULAS"
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " TITULO TEXT NOT NULL,"
                + " DESCRIPCION TEXT NOT NULL,"
                + " CALIFICACION INTEGER NOT NULL,"
                + " DURACION INTEGER NOT NULL,"
                + " LANZAMIENTO INTEGER NOT NULL"
                + ")";
        
        try {
            Statement comando = Conexion.getConexion().createStatement();
            int resultado = comando.executeUpdate(sqlCreate);
            comando.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<PeliculaItem> getPeliculaItems (boolean forceLoad){
        try {
            if (forceLoad) {
                Statement comando = Conexion.getConexion().createStatement();
                ResultSet misRegistros = comando.executeQuery("SELECT * FROM PELICULAS;");
                this._PeliculasItems.clear();
                while (misRegistros.next()){
                    PeliculaItem registro = new PeliculaItem();
                    registro.setId(misRegistros.getInt("ID"));
                    registro.setTitulo(misRegistros.getString("TITULO"));
                    registro.setDescripcion(misRegistros.getString("DESCRIPCION"));
                    registro.setCalificacion(misRegistros.getInt("CALIFICACION"));
                    registro.setDuracion(misRegistros.getInt("DURACION"));
                    registro.setAnioLanzamiento(misRegistros.getInt("LANZAMIENTO"));
                    this._PeliculasItems.add(registro);
                }
            }
            
            return this._PeliculasItems;
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return this._PeliculasItems;
        }
    }
    
    public PeliculaItem getPeliculatemById (int id){
        try {
            String SQLGetById = "SELECT * FROM PELICULAS WHERE ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLGetById);
            comando.setInt(1, id);
            ResultSet misRegistros = comando.executeQuery();
            PeliculaItem registro = new PeliculaItem();
            
            while (misRegistros.next()){
                registro.setId(misRegistros.getInt("ID"));
                registro.setTitulo(misRegistros.getString("TITULO"));
                registro.setDescripcion(misRegistros.getString("DESCRIPCION"));
                registro.setCalificacion(misRegistros.getInt("CALIFICACION"));
                registro.setDuracion(misRegistros.getInt("DURACION"));
                registro.setAnioLanzamiento(misRegistros.getInt("LANZAMIENTO"));
                break;
            }
            comando.close();
            
            return registro;
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public int updatePeliculaItem(PeliculaItem itemToUpdate){
        try {
            String SQLUpdate = "UPDATE PELICULAS set TITULO=?, DESCRIPCION=?, CALIFICACION=?, DURACION=?, LANZAMIENTO=? where ID=?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLUpdate);
            
            comando.setString(1, itemToUpdate.getTitulo());
            comando.setString(2, itemToUpdate.getDescripcion());
            comando.setDouble(3, itemToUpdate.getCalificacion());
            comando.setInt(4, itemToUpdate.getDuracion());
            comando.setInt(5, itemToUpdate.getAnioLanzamiento());
            comando.setInt(6, itemToUpdate.getId());
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    
    
    public int insertPeliculaItem(PeliculaItem itemToInsert){
        try {
            String SQLInsert = "INSERT INTO PELICULAS (TITULO, DESCRIPCION, CALIFICACION, DURACION, LANZAMIENTO) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLInsert);
            
            comando.setString(1, itemToInsert.getTitulo());
            comando.setString(2, itemToInsert.getDescripcion());
            comando.setDouble(3, itemToInsert.getCalificacion());
            comando.setInt(4, itemToInsert.getDuracion());
            comando.setInt(5, itemToInsert.getAnioLanzamiento());
            
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    public int deletePeliculaItem(PeliculaItem itemToDelete){
        try {
            String SQLDelete = "DELETE FROM PELICULAS WHERE ID = ?;";
            PreparedStatement comando = Conexion.getConexion().prepareStatement(SQLDelete);
            
            comando.setInt(1, itemToDelete.getId());
            
            int registroAfectado = comando.executeUpdate();
            comando.close();
            return registroAfectado;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
}
