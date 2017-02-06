package modules;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class FollowModule implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer() instanceof Player && e.getRightClicked() instanceof Entity) {
			Entity target = e.getRightClicked();
			if (Modmode.modlist.contains(p.getName())) {
				e.setCancelled(true);
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						String item = p.getItemInHand().getItemMeta().getDisplayName();
						if (item.equals(
								Main.pl.getConfig().getString("MOD Items.Follow user.NAME").replace("&", "§"))) {
							target.setPassenger(p);
						}
					}
				}
			}
		}
	}
}
