package com.bukkit.Duendek86.tiredman;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
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
* tiredmanPlayerListener.java
*
* Comprueba si el usuario esta moviendose dentro de agua profunda y calcula.
* si tiene suficiente energia o si comienza a ahogarse.
*
* @author Duendek86 <mendezpoli86@gmail.com>
* @author Fran <franrv@gmail.com>
*/


public class tiredmanPlayerListener extends PlayerListener {

	private final TiredMan plugin;
    
    public tiredmanPlayerListener(TiredMan instance) {
        plugin = instance;
    }


    @Override
    public void onPlayerCommandPreprocess(PlayerChatEvent event) {
        super.onPlayerCommandPreprocess(event);
    

    	String permisoinfo = "tiredman.info";
    	String permisoadmin = "tiredman.admin";
	String mensaje;
    	String[] split = event.getMessage().split(" ");
        Player player = event.getPlayer();
        String pname = player.getName();

        if (!event.isCancelled() && (split[0].equalsIgnoreCase("/tiredman") || split[0].equalsIgnoreCase("/tm"))) {
			if (split.length < 2){
		    	if (plugin.permissionsfoud == 1) {
				if (!TiredMan.Permissions.has(player, permisoinfo)) {
			    	    mensaje = "" + Config.getTiredmantext().get("50");
			    	    player.sendMessage(mensaje);
			    		event.setCancelled(true);
			    		return;
			    	}
		    	}
			String enabled = "" +  Config.getTiredmantext().get("28")+".";
	    		String disabled = "" +  Config.getTiredmantext().get("28")+".";

	    		
	    		mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		mensaje = "# Tiredman";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		mensaje = "#" +  Config.getTiredmantext().get("31")+" = "+Config.getConfiguracion().get("languaje");
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		
	    		Integer active = Integer.parseInt((String) Config.getConfiguracion().get("waterfatigue"));
	    		if (active == 1){
	    			mensaje = "#" +  Config.getTiredmantext().get("32")+" = "+enabled;
	    		}else{
	    			mensaje = "#" +  Config.getTiredmantext().get("32")+" = "+disabled;
	    		}    		
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		
	    		mensaje = "#    " +  Config.getTiredmantext().get("33")+" = "+Config.getConfiguracion().get("ewater")+"    "+Config.getTiredmantext().get("34")+" = "+Config.getConfiguracion().get("dierate");
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		
	    		mensaje = "#    " +  Config.getTiredmantext().get("35")+" = "+Config.getConfiguracion().get("dieinwater");
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
	    		
	    		active = Integer.parseInt((String) Config.getConfiguracion().get("landfatigue"));
	    		
	    		if (active == 1){
	    			mensaje = "#" +  Config.getTiredmantext().get("36")+" = "+enabled;
	    		}else{
	    			mensaje = "#" +  Config.getTiredmantext().get("36")+" = "+disabled;
	    		}    		
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);	    		
	    		
	    		mensaje = "#    " +  Config.getTiredmantext().get("37")+" = "+Config.getConfiguracion().get("hungryrate")+"    "+Config.getTiredmantext().get("38")+" = "+Config.getConfiguracion().get("dieinland");
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);

	    		mensaje = "#####################################################";
	    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			}else{

				if (split[1].equalsIgnoreCase("excluded")){
			    	if (plugin.permissionsfoud == 1) {
					if (!TiredMan.Permissions.has(player, permisoadmin)) {
				    	    mensaje = "" + Config.getTiredmantext().get("50");
				    	    player.sendMessage(mensaje);
                                            event.setCancelled(true);
                                            return;
				    	}
			    	}else{
			    		if (!player.isOp()){
				    	    mensaje = "" + Config.getTiredmantext().get("50");
				    	    player.sendMessage(mensaje);
				    		event.setCancelled(true);
				    		return;		    			
			    		}
			    		
			    	} 
		    		mensaje = "#####################################################";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);   
		    		mensaje = "#" + Config.getTiredmantext().get("39");
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "#####################################################";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "#" + Config.getUsersunafected();
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
		    		mensaje = "#####################################################";
		    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
				}else{
					if (split[1].equalsIgnoreCase("info")){
				    	if (plugin.permissionsfoud == 1) {
						if (!TiredMan.Permissions.has(player, permisoinfo)) {
					    	    mensaje = "" + Config.getTiredmantext().get("50");
					    	    player.sendMessage(mensaje);
					    		event.setCancelled(true);
					    		return;
					    	}
				    	}
					mensaje = "#####################################################";
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);   
			    		mensaje = "#" + player.getName();
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			    		mensaje = "#####################################################";
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			    		mensaje = "#" + Config.getTiredmantext().get("40") + " " + Config.basedatos.get(pname);
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			    		mensaje = "#" + Config.getTiredmantext().get("41") + " " + Config.basedatoscansado.get(pname);
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
			    		mensaje = "#####################################################";
			    		player.sendMessage(org.bukkit.ChatColor.GREEN + mensaje);
						
					}else{
					mensaje = "" + Config.getTiredmantext().get("51");
					player.sendMessage(mensaje);
					}
				}
	    		
	    		
	    		event.setCancelled(true);
			}
        }		
    }
    
    //    Cuando un jugador se une lo intruduzco en las bases de datos.
    @Override
    public void onPlayerJoin(PlayerEvent event) {
    	Player player = event.getPlayer();
    	Integer energiamaxima = Integer.parseInt((String) Config.getConfiguracion().get("ewater"));
    	if (Config.basedatos.get(player.getName()) == null){
    		Config.basedatos.put(player.getName(), energiamaxima );
    	}    	    	
    	if (Config.basedatosahogado.get(player.getName()) == null){
    		Config.basedatosahogado.put(player.getName(), 0);
    	}    	
    	if (Config.basedatoscansado.get(player.getName()) == null){
    		Config.basedatoscansado.put(player.getName(), 0);
    	}
    }


    //Cuando un jugador se va lo quito de las bases de datos

//    public void onPlayerQuit(PlayerEvent event) {
//     Player player = event.getPlayer();
//     String njugador = player.getName();
     //plugin.basedatos.remove(njugador);
     //plugin.basedatosahogado.remove(njugador);
     //plugin.basedatoscansado.remove(njugador);
//    }


    
    //Cuando el jugador se mueve comprobamos si esta en el agua profunda.
    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
		Block bloque = null;
		Block bloque2 = null;
		Block bloque3 = null;
		Block bloque4 = null;
		Integer fatigatrue = Integer.parseInt((String) Config.getConfiguracion().get("landfatigue"));

		Location from = event.getFrom ().clone();
		Location to = event.getTo().clone();    
		Player player = event.getPlayer();

                PlayerStatus status = new PlayerStatus(player);
		World mundo = player.getWorld();
		String njugador = player.getName();


                if(Config.getUsersunafected().contains(njugador)) return;

                Integer cansancio = Config.basedatoscansado.get(njugador);
		Integer energia = Config.basedatos.get(njugador);
		Integer energiamaxima = Integer.parseInt((String) Config.getConfiguracion().get("ewater"));
		 
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
                                //player.sendMessage("Saltando");
			}else{
                                if (bloque2.getTypeId() == 2){
                                    if (fatigatrue == 1) status.aumentarcansancio( cansancio, 4); // si camina por grass + 4 de cansancio
                                    //player.sendMessage("Caminando sobre grass");

                                    }else{
                                        if (bloque2.getTypeId() == 3){
                                            if (fatigatrue == 1) status.aumentarcansancio( cansancio, 6); // si camino por dirt + 6 de cansancio
                                            //player.sendMessage("Caminando sobre dirt");

                                        }else{
                                            if ((bloque2.getTypeId() == 12) || (bloque4.getTypeId() == 13)){ // si camina por sand o grave +8 de cansancio
                                                if (fatigatrue == 1) status.aumentarcansancio( cansancio, 8);
                                                // player.sendMessage("Caminando sobre arena o grava");

                                        }else{
                                            if (fatigatrue == 1) status.aumentarcansancio( cansancio, 2); // Por cualquier otra superficio +2 de cansancio

                                        }
                                    }
                             }
			}     
		}
    }


 
}