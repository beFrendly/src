package modules;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

@SuppressWarnings("deprecation")
public class VanishModule implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (Modmode.modlist.contains(p.getName())) {
			e.setCancelled(true);
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.getItemInHand() != null && p.getItemInHand().hasItemMeta()) {
					String item = p.getItemInHand().getItemMeta().getDisplayName();
					if (Modmode.vanishlist.contains(p.getName())) {
						if (item.equals(
								Main.pl.getConfig().getString("MOD Items.Vanish.Enabled.NAME").replace("&", "§"))) {
							for (Player online : Bukkit.getOnlinePlayers()) {
								online.showPlayer(p);
							}
							p.sendMessage(Main.pl.getConfig().getString("Vanish section.Unvanished").replace("&", "§")
									.replace("%staff_name%", p.getName()));

							ItemStack stack = new ItemStack(
									Main.pl.getConfig().getInt("MOD Items.Vanish.Disabled.ITEMID"), 1,
									(short) Main.pl.getConfig().getInt("MOD Items.Vanish.Disabled.SUBID"));
							ItemMeta stackmeta = stack.getItemMeta();
							stackmeta.setDisplayName(
									Main.pl.getConfig().getString("MOD Items.Vanish.Disabled.NAME").replace("&", "§"));
							stack.setItemMeta(stackmeta);
							p.setItemInHand(stack);
							p.updateInventory();
							Modmode.vanishlist.remove(p.getName());

						}
					} else {
						if (item.equals(
								Main.pl.getConfig().getString("MOD Items.Vanish.Disabled.NAME").replace("&", "§"))) {
							for (Player online : Bukkit.getOnlinePlayers()) {
								if (!online.hasPermission("modmode.vanish.bypass")) {
									online.hidePlayer(p);
								}
							}
							p.sendMessage(Main.pl.getConfig().getString("Vanish section.Vanished").replace("&", "§")
									.replace("%staff_name%", p.getName()));

							ItemStack stack = new ItemStack(
									Main.pl.getConfig().getInt("MOD Items.Vanish.Enabled.ITEMID"), 1,
									(short) Main.pl.getConfig().getInt("MOD Items.Vanish.Enabled.SUBID"));
							ItemMeta stackmeta = stack.getItemMeta();
							stackmeta.setDisplayName(
									Main.pl.getConfig().getString("MOD Items.Vanish.Enabled.NAME").replace("&", "§"));
							stack.setItemMeta(stackmeta);
							p.setItemInHand(stack);
							p.updateInventory();
							Modmode.vanishlist.add(p.getName());
						}
					}
				}
			}
		}
	}
}
