package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;


public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		if (Modmode.modlist.contains(p.getName())) {
			e.setCancelled(true);
		}
		if (Main.frozenlist.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

}
