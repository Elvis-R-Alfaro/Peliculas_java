/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodesno.dao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author EAlfa
 */
public class Conexion {
    private static Connection _conexion = null;
    
    private Conexion(){
        
    }
    
    public static Connection getConexion(){
        try {
            
            if (_conexion == null) {
                Class.forName("org.sqlite.JDBC");
                _conexion = DriverManager.getConnection("jdbc:sqlite:peliculasdb.db");
                return  _conexion;
            }else{
                return _conexion;
            }
            
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
            return null;
        }
    }
}
