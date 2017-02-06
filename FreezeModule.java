package modules;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class FreezeModule implements Listener {

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
						if (item.equals(Main.pl.getConfig().getString("MOD Items.Freeze user.NAME").replace("&", "§"))) {
							if (Main.frozenlist.contains(target.getName())) {
								p.sendMessage(Main.pl.getConfig()
										.getString("Messages section.Freeze staff.unfreeze_user_message")
										.replace("&", "§").replace("%target%", target.getName()));
								List<String> unfreezemessage = Main.pl.getConfig()
										.getStringList("Freeze section.Unfreeze message");
								for (String ufm : unfreezemessage) {
									target.sendMessage(ufm.replace("&", "§").replace("%staff_name%", p.getName()));
								}
								Main.frozenlist.remove(target.getName());
							} else {
								Main.frozenlist.add(target.getName());
								p.sendMessage(Main.pl.getConfig()
										.getString("Messages section.Freeze staff.freeze_user_message")
										.replace("&", "§").replace("%target%", target.getName()));
								Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.pl, new Runnable() {

									@Override
									public void run() {
										if (Main.frozenlist.contains(target.getName())) {
											List<String> freezemessage = Main.pl.getConfig()
													.getStringList("Freeze section.Freeze message");
											for (String fm : freezemessage) {
												target.sendMessage(
														fm.replace("&", "§").replace("%staff_name%", p.getName()));
											}

										}
									}

								}, 0, 20 * Main.pl.getConfig().getInt("Freeze section.Settings.Freeze repeat"));
							}
						}
					}
				}
			}
		}
	}
}
