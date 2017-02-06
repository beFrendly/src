package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class DealDamage implements Listener {

	@EventHandler
	public void onDamageDealth(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Modmode.modlist.contains(p.getName())) {
				e.setCancelled(true);
			}
			if (Main.frozenlist.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}

}
