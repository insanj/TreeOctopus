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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

class OctopusBlockListener extends BlockListener
{
	TreeOctopus plugin;

	public OctopusBlockListener(TreeOctopus instance){
		plugin = instance;	
	}

	public void onBlockPlace(BlockPlaceEvent event){

		if( plugin.enabled(event.getPlayer()) ){
			if( event.getBlock().getType().equals(Material.GOLD_BLOCK) && event.getBlockAgainst().getType().equals(Material.LEAVES) ){
				event.getBlock().setTypeId(0);
				event.getBlock().getWorld().spawnCreature(event.getBlock().getLocation(), CreatureType.SQUID);
				event.getPlayer().sendMessage(ChatColor.YELLOW + "Sacrifice accepted!");
				event.getPlayer().sendMessage(ChatColor.GREEN + "You have summoned the all mighty tree octopus!");
			}
		}
	}//end method onCommandPreProcess()

}//end class TreeOctopusListener

class OctopusPlayerListener extends PlayerListener{

	TreeOctopus plugin;

	public OctopusPlayerListener (TreeOctopus instance){
		plugin = instance;	
	}

	public void onPlayerInteract(PlayerInteractEvent event){
		
		if( event.getAction().equals(Action.LEFT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.LEAVES) && 
				event.getItem().getType().equals(Material.GOLD_INGOT) ){

			event.getItem().setAmount(event.getItem().getAmount() - 1);
			event.getClickedBlock().getWorld().spawnCreature(event.getClickedBlock().getLocation(), CreatureType.SQUID);
			event.getPlayer().sendMessage(ChatColor.YELLOW + "Sacrifice accepted!");
			event.getPlayer().sendMessage(ChatColor.GREEN + "You have summoned the all mighty tree octopus!");
		}
	}//end onPlayerInteract()
	
}//end OctopusPlayerListener
