/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodesno.peliculas;

import com.rodesno.utilities.Layout;
import java.util.Scanner;
/**
 *
 * @author EAlfa
 */
public class Main {
    public static void main(String[] args){
        Layout.printHeader("Peliculas V1.0.1");
        String OpcionMenu = "";
        Scanner entradaTeclado = new Scanner(System.in);
        
        Aplication peliculasapp = new Aplication(entradaTeclado); 
        
        while(!(OpcionMenu.toUpperCase().equals("S"))){
            Layout.printMenu();
            OpcionMenu = entradaTeclado.nextLine();
            
            peliculasapp.activarEvento(OpcionMenu);
        }
    }
}
