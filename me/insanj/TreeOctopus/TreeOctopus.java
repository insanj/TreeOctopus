/*
 Created by Julian Weiss (insanj), updates frequent on Google+ (and sometimes Twitter)!

 Please do not modify or decompile at any date, but feel free to distribute with credit.
 Production began on Tuesday, August 2nd, 2011.
 Last edited on: 8/13/11

 Pacific Northwest Tree Octopus Version 1.1!
 Special thanks to: 
 		Camcade, Carlthealpaca, and Gonjigas for design and publicity and pretty much everything but the coding and whatnot.

 		
 Works with the current CraftBukkit Build (#1000).
 All other information should be available at bukkit.org under The Pacific Northwest Tree Octopus.

 Currently supports:
		Permissions plugin, version 3.1.6!

 THIS VERSION CURRENT HAS TWO CLASSES:
			TreeOctopus.java
			TreeOctopusListener.java

*/

package me.insanj.TreeOctopus;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class TreeOctopus extends JavaPlugin
{
	private static final Logger log = Logger.getLogger("Minecraft");
	OctopusBlockListener blockListener = new OctopusBlockListener(this);
	OctopusPlayerListener playerListener = new OctopusPlayerListener(this);
	private static final String version = "1.1";
	
	ArrayList<Player> users = new ArrayList<Player>();
	public static boolean permissions;
	public PermissionHandler permissionHandler;
	

	@Override
	public void onEnable(){		
		setupPermissions();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		log.info("{TreeOctopus} plugin version " + version + " has successfully started.");
	}
	
	@Override
	public void onDisable() {
		log.info("{TreeOctopus} plugin version " + version + " disabled.");	
	}
	
	private void setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (this.permissionHandler == null) {
			if (permissionsPlugin != null) 
				this.permissionHandler = ((Permissions) permissionsPlugin).getHandler();

			else{
				log.info("{TreeOctopus} could not detect a Permissions system, which is fine, but everyone will have to use the command to disable.");
				permissions = false;
			} 
		}//end if
	}//end setupPermissions()
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
	{
		if(commandLabel.equalsIgnoreCase("octopus")){
			sender.sendMessage(ChatColor.RED + "You have disabled the TreeOctopus plugin. Farewell, traveler.");
			users.add((Player) sender);
		}
			
		return true;
		
	}//end method onCommand()
	
	public boolean enabled(Player player){
		if( permissions == false && !users.contains(player) )
			return true;
		
		else if( permissions == true && permissionHandler.has(player, "TreeOctopus.disable") )
			return false;
		
		return false;
		
	}//end enabled()
	
}//end class TreeOctopus

/***********************************Contents of "plugin.yml":*******************************
name: TreeOctopus
version: 1.0
author: insanj
main: me.insanj.TreeOctopus.TreeOctopus
description: Placing gold on trees summons the holy Tree Octopus.
website: http://forums.bukkit.org/threads/the-pacific-northwest-tree-octopus.29301/

commands:
  octopus:
    #Use the following permission as a substitute for this command.
    permissions: replicator.disable
    description: Disables the plugin, because it is enabled by default.
    usage: /<command>

******************************************************************************************/