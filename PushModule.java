package modules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class PushModule implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Modmode.modlist.contains(p.getName())) {
			e.setCancelled(true);
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand() != null && p.getItemInHand().hasItemMeta()) {
					String item = p.getItemInHand().getItemMeta().getDisplayName();
					if (item.contains(Main.pl.getConfig().getString("MOD Items.Push.NAME").replace("&", "§"))) {
						p.setVelocity(p.getEyeLocation().getDirection().multiply(2));
					}
				}
			}
		}
	}
}
