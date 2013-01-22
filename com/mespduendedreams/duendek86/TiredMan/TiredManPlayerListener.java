/**
 * TiredMan 0.8 Copyright (c) 2013 Duendek86 <mendezpoli86@gmail.com>
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

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * tiredmanPlayerListener.java
 * 
 * Comprueba si el usuario esta moviendose dentro de agua profunda y calcula. si
 * tiene suficiente energia o si comienza a ahogarse.
 * 
 * @author Duendek86 <mendezpoli86@gmail.com>
 * @author Fran <franrv@gmail.com>
 */

public class TiredManPlayerListener implements Listener
{
	TiredMan plugin;

	public TiredManPlayerListener( TiredMan instance )
	{
		plugin = instance;
	}

	public void initalizePlayer( Player player )
	{
		Integer maxEnergy = Integer.parseInt( (String) Config.getConfiguration( ).get( "ewater" ) );
		if( Config.basedatos.get( player.getName( ) ) == null )
		{
			Config.basedatos.put( player.getName( ), maxEnergy );
		}
		if( Config.basedatosahogado.get( player.getName( ) ) == null )
		{
			Config.basedatosahogado.put( player.getName( ), 0 );
		}
		if( Config.basedatoscansado.get( player.getName( ) ) == null )
		{
			Config.basedatoscansado.put( player.getName( ), 0 );
		}
	}

	// Cuando un jugador se une lo intruduzco en las bases de datos.

	@EventHandler
	public void onPlayerRespawn( PlayerRespawnEvent event )
	{
		if( event.getPlayer().hasPermission("tiredman.land") || event.getPlayer().hasPermission("tiredman.water") )
		{
			initalizePlayer( event.getPlayer());
		}
	}
	
	@EventHandler
	public void onPlayerJoin( PlayerJoinEvent event )
	{
		if( event.getPlayer().hasPermission("tiredman.land") || event.getPlayer().hasPermission("tiredman.water") )
		{
			initalizePlayer( event.getPlayer( ) );
		}
	}

	// Cuando un jugador se va lo quito de las bases de datos

	// public void onPlayerQuit(PlayerEvent event) {
	// Player player = event.getPlayer();
	// String njugador = player.getName();
	// plugin.basedatos.remove(njugador);
	// plugin.basedatosahogado.remove(njugador);
	// plugin.basedatoscansado.remove(njugador);
	// }

	// Cuando el jugador se mueve comprobamos si esta en el agua profunda.
	@EventHandler
	public void onPlayerMove( PlayerMoveEvent event )
	{
		if( event.getPlayer().hasPermission("tiredman.land") || event.getPlayer().hasPermission("tiredman.water") )
		{
			Block bloque = null;
			Block bloque2 = null;
			Block bloque3 = null;
			Block bloque4 = null;
			Integer fatigatrue = Integer.parseInt((String) Config.getConfiguration().get("landfatigue"));
	
			Location from = event.getFrom ().clone();
			Location to = event.getTo().clone();    
			Player player = event.getPlayer();
	
	                PlayerStatus status = new PlayerStatus(player);
			World mundo = player.getWorld();
			String njugador = player.getName();
	
	
	                //if(Config.getUsersunafected().contains(njugador)) return;
	
	        Integer cansancio = Config.basedatoscansado.get(njugador);
			Integer energia = Config.basedatos.get(njugador);
			Integer energiamaxima = Integer.parseInt((String) Config.getConfiguration().get("ewater"));
	
			bloque = mundo.getBlockAt((int)from.getX(), (int)(from.getY()), (int)from.getZ());
			bloque2 = mundo.getBlockAt((int)from.getX(), (int)(from.getY() - 1), (int)from.getZ());
			bloque3 = mundo.getBlockAt((int)from.getX(), (int)(from.getY() + 1), (int)from.getZ());
			bloque4 = mundo.getBlockAt((int)from.getX(), (int)(from.getY() - 2), (int)from.getZ());
			if ((bloque.getTypeId() == 9) && ((bloque2.getTypeId() == 9) || (bloque3.getTypeId() == 9))){
				status.jugadorenagua( njugador, energia, energiamaxima, from, to, bloque2.getTypeId(), bloque4.getTypeId(), cansancio);
			}else{
				if (energia<energiamaxima){
					status.jugadorrecuperando( njugador, energia, energiamaxima);
				}else{
					if (energia>energiamaxima){
						energia=energiamaxima;
					}
				}
				if(to.getY() > from.getY()) {
					if (fatigatrue == 1) status.aumentarcansancio( cansancio, 5); // si salta +5 de cansancio
	                               // player.sendMessage("Saltando");
				}else{
	                                if (bloque2.getTypeId() == 2){
	                                    if (fatigatrue == 1) status.aumentarcansancio( cansancio, 4); // si camina por grass + 4 de cansancio
	                                   // player.sendMessage("Caminando sobre grass");
	
	                                    }else{
	                                        if (bloque2.getTypeId() == 3){
	                                            if (fatigatrue == 1) status.aumentarcansancio( cansancio, 6); // si camino por dirt + 6 de cansancio
	                                         //   player.sendMessage("Caminando sobre dirt");
	
	                                        }else{
	                                            if ((bloque2.getTypeId() == 12) || (bloque4.getTypeId() == 13)){ // si camina por sand o grave +8 de cansancio
	                                                if (fatigatrue == 1) status.aumentarcansancio( cansancio, 8);
	                                              //  player.sendMessage("Caminando sobre arena o grava");
	
	                                        }else{
	                                            if (fatigatrue == 1) status.aumentarcansancio( cansancio, 2); // Por cualquier otra superficio +2 de cansancio
	
	                                        }
	                                    }
	                             }
				}     
			}
	    }
	}
}