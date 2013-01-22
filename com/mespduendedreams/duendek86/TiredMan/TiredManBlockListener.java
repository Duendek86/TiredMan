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

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;


/**
 * tiredmanBlockListener.java
 * 
 * Comprueba si el usuario esta moviendose dentro de agua profunda y calcula. si
 * tiene suficiente energia o si comienza a ahogarse.
 * 
 * @author Duendek86 <mendezpoli86@gmail.com>
 * @author Fran <franrv@gmail.com>
 * @author mabako <mabako@gmail.com>
 */

public class TiredManBlockListener implements Listener
{
	//TiredMan plugin;

/*	public TiredManBlockListener( TiredMan instance )
	{
		plugin = instance;
	} */

	@EventHandler
	public void onBlockDamage( BlockDamageEvent event )
	{
		if( event.getPlayer().hasPermission("tiredman.land") )
		{
			Integer fatigatrue = Integer.parseInt((String) Config.getConfiguration().get("landfatigue"));
	    	if (fatigatrue == 1){
	    		Player player = event.getPlayer();
	
	                PlayerStatus status = new PlayerStatus(player);
	
	    		String njugador = player.getName();
	    		if (Config.getConfiguration().contains(njugador)){
	    				return;
	    			}
	    		Integer cansancio = Config.basedatoscansado.get(njugador);
	    		status.aumentarcansancio(cansancio, 15);
	    	}
		}
	}

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) 
	{
		if( event.getPlayer().hasPermission("tiredman.land") )
			{
	    	Integer fatigatrue = Integer.parseInt((String) Config.getConfiguration().get("landfatigue"));
	    	if (fatigatrue == 1){ 
		    	Player player = event.getPlayer();
	            PlayerStatus status = new PlayerStatus(player);
	            String njugador = player.getName();
	            Integer cansancio = Config.basedatoscansado.get(njugador);
	            status.aumentarcansancio(cansancio, 8);
	    	}
		}
	}
    
}