package com.bukkit.Duendek86.tiredman;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
*  TiredMan 0.5
* Copyright (C) 2011 Duendek86 <mendezpoli86@gmail.com>, Fran
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/**
* Config.java
*
* Comprueba si el usuario esta moviendose dentro de agua profunda y calcula.
* si tiene suficiente energia o si comienza a ahogarse.
*
* @author Duendek86 <mendezpoli86@gmail.com>
* @author Fran
*/
public class Config{

    private static String dataFolderPath;
    private static String configPath;
    private static String excludedPath;
    private static Properties configuracion;
    private static Properties tiredmantext;
    private static ArrayList<String> usersunafected;
    public static HashMap<String, Integer> basedatos = new HashMap();
    public static HashMap<String, Integer> basedatosahogado = new HashMap();
    public static HashMap<String, Integer> basedatoscansado = new HashMap();

    public static void loadConfig(File dataFolder) throws IOException {
        dataFolderPath = dataFolder.toString();
        configPath =  dataFolderPath + File.separator+"tiredman.properties";
        excludedPath = dataFolderPath +File.separator+ "excluded.txt";   
        configuracion = new Properties();
        tiredmantext = new Properties();

        FileInputStream fiConfig = new FileInputStream(configPath);
        configuracion.load(fiConfig);
        FileInputStream fiText = new FileInputStream(dataFolderPath +File.separator +"languaje_" + configuracion.get("languaje") + ".properties");
        tiredmantext.load(fiText);
        loadExcluded();
        fiConfig.close();
        fiText.close();

    }

    public static void loadExcluded() throws  IOException {
        usersunafected = new ArrayList<String>();
            FileReader archivoexcluded = new FileReader(excludedPath);
    	    Scanner escanerexcluded = new Scanner(archivoexcluded);
    	    while (escanerexcluded.hasNextLine()){
    	    	Scanner lineaexcluded = new Scanner(escanerexcluded.nextLine());
   	            usersunafected.add(lineaexcluded.next());
    	    	}
    	    escanerexcluded.close();
    	    archivoexcluded.close();
    }

    public static Properties getConfiguracion() {
        return configuracion;
    }

    public static Properties getTiredmantext() {
        return tiredmantext;
    }

    public static ArrayList<String> getUsersunafected() {
        return usersunafected;
    }

    public static HashMap<String, Integer> getBasedatos() {
        return basedatos;
    }

    public static HashMap<String, Integer> getBasedatosahogado() {
        return basedatosahogado;
    }

    public static HashMap<String, Integer> getBasedatoscansado() {
        return basedatoscansado;
    }

}
