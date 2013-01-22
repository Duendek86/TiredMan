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

/**
 * TiredMan.java
 * 
 * Clase principal.
 * 
 * @author Duendek86 <mendezpoli86@gmail.com>
 * @author Fran <franrv@gmail.com>
 * @author mabako <mabako@gmail.com>
*/
import java.io.IOException;
//import java.util.HashMap;
import java.util.logging.Logger;

//import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TiredMan extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static TiredMan plugin;	
	private final TiredManPlayerListener playerListener = new TiredManPlayerListener( this );
	private final TiredManBlockListener blockListener = new TiredManBlockListener();

	public void onEnable( )
	{
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " has been enabled!");
		try
		{
			Config.loadConfig( getDataFolder( ) );
	        // Register our events
	        PluginManager pm = getServer().getPluginManager();
	        pm.registerEvents(playerListener, this);
	        pm.registerEvents(blockListener, this);

			getCommand( "tiredman" ).setExecutor( new TiredManCommand( ) );

/*			// Load all connected users
			for( Player player : getServer( ).getOnlinePlayers( ) )
				playerListener.initalizePlayer( player );
*/
			System.out.println( pdfFile.getName( ) + " " + Config.getTranslation( 1 ) + " " + pdfFile.getVersion( ) + " " + Config.getTranslation( 2 ) );
		}
		catch( IOException e )
		{
			System.out.println( e );
			System.out.println( "[ERROR] tiredman couldn't load config files. Did you copy tiredman folder into your plugin folder?" );
		}

	}

	@Override
	public void onDisable( )
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled!");
	}

}