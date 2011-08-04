/*
 Created by Julian Weiss (insanj), updates frequent on Google+ (and sometimes Twitter)!

 Please do not modify or decompile at any date, but feel free to distribute with credit.
 Production began on Tuesday, August 2nd, 2011.
 Last edited on: 8/2/11

 Pacific Northwest Tree Octopus Version 1.0!
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

import org.bukkit.ChatColor; 
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TreeOctopusListener extends BlockListener
{
	TreeOctopus plugin;
	
	public TreeOctopusListener(TreeOctopus instance){
		plugin = instance;	
	}//end method TreeOctopusListener
	
    public void onBlockPlace(BlockPlaceEvent event){
		
    	if( plugin.enabled(event.getPlayer()) )
    		if((event.getBlock().getTypeId() == 41) && (new Location(event.getBlock().getWorld(), event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY() - 1, event.getBlock().getLocation().getZ())).getBlock().getTypeId() == 18 ){
    			event.getPlayer().sendMessage(ChatColor.YELLOW + "Sacrifice accepted. You have summoned the all mighty tree octopus!");
    			event.getBlock().setTypeId(0);
    			event.getBlock().getWorld().spawnCreature(event.getBlock().getLocation(), CreatureType.SQUID);
    		}//end if
    	
	}//end method onCommandPreProcess()
	
}//end class TreeOctopusListener
