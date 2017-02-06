package modules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class EnderseeModule implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer() instanceof Player && e.getRightClicked() instanceof Player) {
			Player target = (Player) e.getRightClicked();
			if (Modmode.modlist.contains(p.getName())) {
				e.setCancelled(true);
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						String item = p.getItemInHand().getItemMeta().getDisplayName();
						if (item.equals(
								Main.pl.getConfig().getString("MOD Items.Enderchest user.NAME").replace("&", "§"))) {
							p.openInventory(target.getEnderChest());
							p.sendMessage(
									Main.pl.getConfig().getString("Messages section.Enderchest see.opening_message")
											.replace("&", "§").replace("%target%", target.getName()));
						}
					}
				}
			}
		}
	}
}
