package com.bukkit.Duendek86.tiredman;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
*  TiredMan 0.5
* Copyright (C) 2011 Duendek86 <mendezpoli86@gmail.com>, Fran <franrv@gmail.com>
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
* tiredmanBlockListener.java
*
* Comprueba si el usuario esta moviendose dentro de agua profunda y calcula.
* si tiene suficiente energia o si comienza a ahogarse.
*
* @author Duendek86 <mendezpoli86@gmail.com>
* @author Fran <franrv@gmail.com>
*/


public class tiredmanBlockListener extends BlockListener {

	private final TiredMan plugin;

    public tiredmanBlockListener(final TiredMan instance) {
        plugin = instance;
    }

    //put all Block related code here

    
    @Override
    public void onBlockDamage(BlockDamageEvent event){
		Integer fatigatrue = Integer.parseInt((String) Config.getConfiguracion().get("landfatigue"));
    	if (fatigatrue == 1){
    		Player player = event.getPlayer();

                PlayerStatus status = new PlayerStatus(player);

    		String njugador = player.getName();
    		if (Config.getConfiguracion().contains(njugador)){
    				return;
    			}
    		Integer cansancio = Config.basedatoscansado.get(njugador);
    		status.aumentarcansancio(cansancio, 15);
    	}
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event){
		Integer fatigatrue = Integer.parseInt((String) Config.getConfiguracion().get("landfatigue"));
    	if (fatigatrue == 1){ 
	    	Player player = event.getPlayer();

                PlayerStatus status = new PlayerStatus(player);

                String njugador = player.getName();
			for (int i = 0; i<Config.getUsersunafected().size(); i++){
				if (njugador.equalsIgnoreCase((String) Config.getUsersunafected().get(i))){
					return;	
				}
			}
				
	        Integer cansancio = Config.basedatoscansado.get(njugador);
			status.aumentarcansancio(cansancio, 8);
    	}
    }
}