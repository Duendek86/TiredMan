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

/**
 * TiredManCommand.java
 * 
 * Controla los comandos introducidos.
 * 
 * @author Duendek86 <mendezpoli86@gmail.com>
 * @author mabako <mabako@gmail.com>
*/

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TiredManCommand implements CommandExecutor
{
//	private final TiredMan plugin;

//	public TiredManCommand( TiredMan instance )
//	{
//		plugin = instance;
//	}
	@Override
	public boolean onCommand( CommandSender sender, Command command, String label, String[ ] args )
	{
		// Not exactly sure how to get it to work with command sender
		String mensaje;

		if( !( sender instanceof Player) )
			return false;
		Player player = (Player) sender;

		if( args.length == 0 )
		{
			if( player.hasPermission("tiredman.info") )
			{
				//Player affected = args.length == 1 ? player : plugin.getServer( ).getPlayer( args[0] );
				if( player == null || !player.isOnline( ) )
				{
					player.sendMessage( ChatColor.RED + Config.getTranslation( 52 ) );
					return true;
				}
				mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);   
				player.sendMessage( ChatColor.GREEN + "# " + player.getName( ) );
				mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
				player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 40 ) + ": " + Config.basedatos.get( player.getName( ) ) );
				player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 41 ) + ": " + Config.basedatoscansado.get( player.getName( ) ) );
				mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			}
		}
		else
		{
			if( player.hasPermission("tiredman.admin") )
			{
				if( args[0].equalsIgnoreCase( "info" ) )
				{
					//  Allows 'info' for other users
					mensaje = "#####################################################";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "# Tiredman";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "#####################################################";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
					player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 31) + " = " + Config.getConfiguration( ).getProperty( "languaje" ) );

					int waterFatigue = Integer.parseInt( (String)Config.getConfiguration( ).get( "waterfatigue" ) );
					player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 32 ) + ": " + Config.getTranslation( 29 - waterFatigue ) );
					if( waterFatigue == 1 )
					{
						player.sendMessage( ChatColor.GREEN + "#    " + Config.getTranslation( 33 ) + ": " + Config.getConfiguration( ).get( "ewater" ) + " | " + Config.getTranslation( 34 ) + ": " + Config.getConfiguration( ).get( "dierate" ) );
						player.sendMessage( ChatColor.GREEN + "#    " + Config.getTranslation( 35 ) + ": " + Config.getConfiguration( ).get( "dieinwater" ) );
					}

					int landFatigue = Integer.parseInt( (String) Config.getConfiguration( ).get( "landfatigue" ) );
					player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 36 ) + ": " + Config.getTranslation( 29 - landFatigue ) );
					if( landFatigue == 1 )
						player.sendMessage( ChatColor.GREEN + "#  " + Config.getTranslation( 37 ) + ": " + Config.getConfiguration( ).get( "hungryrate" ) + " | " + Config.getTranslation( 38 ) + ": " + Config.getConfiguration( ).get( "dieinland" ) );
					player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "#####################################################";
				}
				else
					player.sendMessage( ChatColor.RED + Config.getTranslation( 51 ) );
			}
		}
		return true;
	}
}