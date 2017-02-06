package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class ReceiveDamage implements Listener {

	@EventHandler
	public void onTakeDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Modmode.modlist.contains(e.getDamager().getName())) {
				e.setCancelled(true);
			}
			if (Main.frozenlist.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
