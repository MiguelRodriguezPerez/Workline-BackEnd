package com.example.demo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;

public class LeerCSV {

    public static ArrayList<String> procesarCSV(String rutaArchivo) {
        ArrayList<String> resultado = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(rutaArchivo).getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                
                resultado.add(linea);
                // String[] campos = linea.split(",");
                // // Procesa los campos como necesites
                // System.out.println(campos[0] + " | " + campos[1] + " | " + campos[2]);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
