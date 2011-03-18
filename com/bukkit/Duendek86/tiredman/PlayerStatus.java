/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bukkit.Duendek86.tiredman;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
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
* tiredmanPlayerStatus.java
*
* Comprueba si el usuario esta moviendose dentro de agua profunda y calcula.
* si tiene suficiente energia o si comienza a ahogarse.
*
* @author Duendek86 <mendezpoli86@gmail.com>
* @author Fran <franrv@gmail.com>
*/
public class PlayerStatus {
    private Player player;

    PlayerStatus(Player player) {
        this.player = player;
    }

    public void aumentarcansancio(Integer cansancio, Integer cantidad){
		 cansancio = cansancio + cantidad;
		 String njugador = player.getName();
		 Config.basedatoscansado.remove(njugador);
		 Config.basedatoscansado.put(njugador, cansancio);
		 compruebacansancio();
   }

    public void compruebacansancio(){
    	Integer hungryrate = Integer.parseInt((String) Config.getConfiguracion().get("hungryrate"));
    	Integer cansancioactual = Config.basedatoscansado.get(player.getName());
    	if (cansancioactual > hungryrate){
    		disminuyevida(3);
    	}
    }

    public void disminuyevida(Integer cantidad){
    	World mundo = player.getWorld();
    	Integer dieinland = Integer.parseInt((String) Config.getConfiguracion().get("dieinland"));
    	Integer vida = player.getHealth();
        vida = vida - cantidad;
        if (vida <= 0) {
        	if (dieinland == 1){
        		vida = 0;
        		ItemStack[] items = player.getInventory().getContents();
        		Location posicion = player.getLocation();
                player.setHealth(vida);
        		for(int i = 0; i < items.length; i++)
        		{
        		    ItemStack is = items[i];
        		    if(is != null && is.getAmount() > 0)
        		    {
        		        mundo.dropItem(posicion, is);
        		    }
        		}
        	}else{
        		vida = 1;
        	}
        	String mensaje = "" + Config.getTiredmantext().get("13");
        	player.sendMessage(mensaje);
        }else{
    	String mensaje = "" + Config.getTiredmantext().get("12");
    	player.sendMessage(mensaje);
        }
    	Config.basedatoscansado.remove(player.getName());
    	Config.basedatoscansado.put(player.getName(), 0);
        player.setHealth(vida);
    }

    public void jugadorenagua( String njugador, Integer energia, Integer energiamaxima, Location from, Location to, Integer bloque2id, Integer bloque4id, Integer cansancio) {
		Integer dierate = Integer.parseInt((String) Config.getConfiguracion().get("dierate"));
		Integer fatigatrue = Integer.parseInt((String) Config.getConfiguracion().get("landfatigue"));
		Integer fatigawatertrue = Integer.parseInt((String) Config.getConfiguracion().get("waterfatigue"));
    	Integer dieinwater = Integer.parseInt((String) Config.getConfiguracion().get("dieinwater"));
		String mensaje = "";
		World mundo = player.getWorld();

		 if (energia > 0) {
			 if(to.getY() <= from.getY()) {
				 if ((to.getY() == from.getY()) && (fatigatrue == 1)) aumentarcansancio( cansancio, 5);
				 if (fatigawatertrue == 1) {
					 disminuyeenergia( njugador, energiamaxima, energia, 5);
				 }
			 }else{
				 if (energia%10 != 0){
					 energia = (energia - energia%10);
				 }
				 if (fatigatrue == 1) aumentarcansancio( cansancio, 10);
				 if (fatigawatertrue == 1){
					 disminuyeenergia(njugador, energiamaxima, energia, 10);

				 }
			 }
		 }else{
			 Integer vecesahogado;
			 Integer desplazamiento = 0;
			 vecesahogado = Config.basedatosahogado.get(njugador);
			 Config.basedatosahogado.remove(njugador);
			 Config.basedatosahogado.put(njugador, vecesahogado + 1);
			 Integer vida;
			 vida = player.getHealth();
			 if (vida>3){
				 if (vecesahogado % (dierate * 10) == 0){
					 player.setHealth(vida - 3);
					 mensaje = mensaje + Config.getTiredmantext().get("11");
		    		 player.sendMessage(mensaje);

					 if (vecesahogado % (dierate * 20) == 0){
						 if (bloque2id == 9) {
							 desplazamiento = 1;
							 if (bloque4id == 9) desplazamiento = 2;
						 }
			    		 from.setY(from.getY() - desplazamiento);
			    		 Random rand = new Random();
			    		 float x = (rand.nextFloat() * rand.nextInt(360));
			    		 from.setPitch(x);
		    			 player.teleportTo(from);
					 }
				 }
			 }else{
				 if (dieinwater == 1){
					 mensaje = mensaje + Config.getTiredmantext().get("11");
	        		ItemStack[] items = player.getInventory().getContents();
	        		Location posicion = player.getLocation();
	                player.setHealth(0);
	        		for(int i = 0; i < items.length; i++)
	        		{
	        		    ItemStack is = items[i];
	        		    if(is != null && is.getAmount() > 0)
	        		    {
	        		        mundo.dropItem(posicion, is);
	        		    }
	        		}
				 }else{
					 player.setHealth(1);
				 }
				 Config.basedatos.remove(njugador);
				 Config.basedatos.put(njugador, energiamaxima);
				 Config.basedatosahogado.remove(njugador);
				 Config.basedatosahogado.put(njugador, 0);
				 Config.basedatoscansado.get(njugador);
				 Config.basedatoscansado.put(njugador, 0);
			 }
		 }
    }

    public void jugadorrecuperando( String njugador, Integer energia, Integer energiamaxima){
		 if (energia%10 != 0){
			 energia = (energia - energia%10);
		 }
		 	aumentarenergia(njugador, energiamaxima, energia, 10);
			Config.basedatosahogado.remove(njugador);
			Config.basedatosahogado.put(njugador, 0);


   }

    public void disminuyeenergia( String njugador, Integer energiamaxima, Integer energia, Integer cantidad){
		 Integer energiavieja = energia;
                 energia = energia - cantidad;
		 Config.basedatos.remove(njugador);
		 Config.basedatos.put(njugador, energia);
		 if ((energiavieja*100/energiamaxima > 80) && (energia*100/energiamaxima <= 80)){
			String mensaje = "" + Config.getTiredmantext().get("3");
			if (!(Config.getTiredmantext().get("3") == null)){
				player.sendMessage(mensaje);
			}
		 }else{
			 if ((energiavieja*100/energiamaxima > 60) && (energia*100/energiamaxima <= 60)){
				String mensaje = "" + Config.getTiredmantext().get("4");
				if (!(Config.getTiredmantext().get("4") == null)){
					player.sendMessage(mensaje);
				}
			 }else{
				if ((energiavieja*100/energiamaxima > 40) && (energia*100/energiamaxima <= 40)){
					String mensaje = "" + Config.getTiredmantext().get("5");
					if (!(Config.getTiredmantext().get("5") == null)){
						player.sendMessage(mensaje);
					}
				}else{
                                        if ((energiavieja*100/energiamaxima > 20) && (energia*100/energiamaxima <= 20)){
					String mensaje = "" + Config.getTiredmantext().get("6");
					if (!(Config.getTiredmantext().get("6") == null)){
						player.sendMessage(mensaje);
                                        }
                            }
		 }
            }
        }
    }

    public void aumentarenergia( String njugador,Integer energiamaxima, Integer energia, Integer cantidad){
		 Integer energiavieja = energia;
    	 energia = energia + cantidad;
		 Config.basedatos.remove(njugador);
		 Config.basedatos.put(njugador, energia);
		 if ((energiavieja*100/energiamaxima < 20) && (energia*100/energiamaxima >= 20)){
			String mensaje = "" + Config.getTiredmantext().get("7");
			if (!(Config.getTiredmantext().get("7") == null)){
				player.sendMessage(mensaje);
			}
		 }else{
			 if ((energiavieja*100/energiamaxima < 40) && (energia*100/energiamaxima >= 40)){
				String mensaje = "" + Config.getTiredmantext().get("8");
				if (!(Config.getTiredmantext().get("8") == null)){
					player.sendMessage(mensaje);
				}
			 }else{
				if ((energiavieja*100/energiamaxima < 60) && (energia*100/energiamaxima >= 60)){
					String mensaje = "" + Config.getTiredmantext().get("9");
					if (!(Config.getTiredmantext().get("9") == null)){
						player.sendMessage(mensaje);
					}
				}else{
                                    if ((energiavieja*100/energiamaxima < 80) && (energia*100/energiamaxima >= 80)){
					String mensaje = "" + Config.getTiredmantext().get("10");
					if (!(Config.getTiredmantext().get("10") == null)){
						player.sendMessage(mensaje);
					}

                                }
			 }
		 }
        }

    }
}
