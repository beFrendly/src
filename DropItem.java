package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class DropItem implements Listener {
	
	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (Modmode.modlist.contains(p.getName())) {
			e.setCancelled(true);
		}
		if (Main.frozenlist.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

}
