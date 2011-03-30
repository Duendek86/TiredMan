package com.bukkit.Duendek86.tiredman;

import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;
import org.bukkit.plugin.Plugin;

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


public class TiredMan extends JavaPlugin{
	private final tiredmanPlayerListener playerListener = new tiredmanPlayerListener(this);
	private final tiredmanBlockListener blockListener = new tiredmanBlockListener(this);
        static PermissionHandler Permissions = null;
        public int permissionsfoud = 1;

	public void onEnable() {

            PluginDescriptionFile pdfFile = this.getDescription();
                    setupPermissions(pdfFile);
            try {
                Config.loadConfig(getDataFolder());
                registerEvents();
                System.out.println( pdfFile.getName() + " " + Config.getTiredmantext().get("1") + " " + pdfFile.getVersion()+ " " + Config.getTiredmantext().get("2"));
            }
            catch ( IOException e ) {
                    System.out.println(e);
                    System.out.println( "[ERROR] tiredman couldn't load config files. Did you copy tiredman folder into your plugin folder?");
            }

        }
    
        public void setupPermissions(PluginDescriptionFile pdfFile) {
            Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
            if(TiredMan.Permissions == null) {
                if(test != null) {
                    TiredMan.Permissions = ((Permissions)test).getHandler();
                } else {
                    System.out.println( "[WARNING] Permissions plugin not detected, Tiredman admin commands only to ops.");
                    //this.getServer().getPluginManager().disablePlugin(this);
                    permissionsfoud = 0;
                }
            //permissionsfoud = 0;
            }
        }
    
	private void registerEvents(){
             getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
             getServer().getPluginManager().registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Normal, this);
             getServer().getPluginManager().registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
             getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACE , blockListener, Priority.Normal, this);
             getServer().getPluginManager().registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Priority.Normal, this);
             getServer().getPluginManager().registerEvent(Event.Type.PLAYER_CHAT , playerListener, Priority.Normal, this);
        }

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}


}

