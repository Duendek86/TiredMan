/**
 * TiredMan 0.8.1 Copyright (c) 2013 Duendek86 <mendezpoli86@gmail.com>
 * TiredMan 0.7 Copyright (c) 2011 mabako <mabako@gmail.com>
 * TiredMan 0.5 Copyright (C) 2011 Duendek86 <mendezpoli86@gmail.com>, Fran
 * <franrv@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mespduendedreams.duendek86.TiredMan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.util.HashMap;

/**
 * Config.java
 * 
 * Comprueba si el usuario esta moviendose dentro de agua profunda y calcula. si
 * tiene suficiente energia o si comienza a ahogarse.
 * 
 * @author Duendek86 <mendezpoli86@gmail.com>
 * @author Fran <franrv@gmail.com>
 * @author mabako <mabako@gmail.com>
 */
public class Config
{

	private static String dataFolderPath;
	private static String configPath;
	private static Properties configuration;
	private static Properties tiredManText;
	public static HashMap< String, Integer > basedatos = new HashMap< String, Integer >( );
	public static HashMap< String, Integer > basedatosahogado = new HashMap< String, Integer >( );
	public static HashMap< String, Integer > basedatoscansado = new HashMap< String, Integer >( );

	public static void loadConfig( File dataFolder ) throws IOException
	{
		dataFolderPath = dataFolder.toString( );
		configPath = dataFolderPath + File.separator + "tiredman.properties";
		configuration = new Properties( );
		tiredManText = new Properties( );

		FileInputStream configFile = new FileInputStream( configPath );
		configuration.load( configFile );
		FileInputStream textFile = new FileInputStream( dataFolderPath + File.separator + "languaje_" + configuration.get( "languaje" ) + ".properties" );
		tiredManText.load( textFile );
		configFile.close( );
		textFile.close( );

	}

	public static Properties getConfiguration( )
	{
		return configuration;
	}

	/*
	public static Properties getTiredManText( )
	{
		return tiredManText;
	}
	*/
	public static String getTranslation( int id )
	{
		String s = tiredManText.get( String.valueOf( id ) ).toString( );
		if( s == null )
			s = "";
		return s;
	}

	public static HashMap< String, Integer > getBasedatos( )
	{
		return basedatos;
	}

	public static HashMap< String, Integer > getBasedatosahogado( )
	{
		return basedatosahogado;
	}

	public static HashMap< String, Integer > getBasedatoscansado( )
	{
		return basedatoscansado;
	}

}