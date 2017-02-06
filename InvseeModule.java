package modules;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class InvseeModule implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();

		if (e.getPlayer() instanceof Player && e.getRightClicked() instanceof Player) {
			Player target = (Player) e.getRightClicked();
			if (Modmode.modlist.contains(p.getName())) {
				e.setCancelled(true);
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						String item = p.getItemInHand().getItemMeta().getDisplayName();
						if (item.equals(
								Main.pl.getConfig().getString("MOD Items.Inventory user.NAME").replace("&", "§"))) {

							Inventory inv = Bukkit.createInventory(p, 54, "§6Inspecting§f: §8" + target.getName());

							ItemStack armorstand = new ItemStack(Material.ARMOR_STAND);
							ItemMeta armorstandMeta = armorstand.getItemMeta();
							armorstandMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							armorstandMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
							armorstandMeta.setDisplayName("§6None");
							armorstand.setItemMeta(armorstandMeta);

							if (target.getInventory().getHelmet() == null)
								inv.setItem(0, armorstand);
							else
								inv.setItem(0, target.getInventory().getHelmet());

							if (target.getInventory().getHelmet() == null)
								inv.setItem(1, armorstand);
							else
								inv.setItem(1, target.getInventory().getChestplate());

							if (target.getInventory().getLeggings() == null)
								inv.setItem(2, armorstand);
							else
								inv.setItem(2, target.getInventory().getLeggings());

							if (target.getInventory().getBoots() == null)
								inv.setItem(3, armorstand);
							else
								inv.setItem(3, target.getInventory().getBoots());

							ItemStack tskull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
							SkullMeta tskullMeta = (SkullMeta) tskull.getItemMeta();
							tskullMeta.setOwner(target.getName());
							tskullMeta.setDisplayName("   §8§m-»§r §6User §f" + target.getName() + " §8§m«-");
							tskullMeta.setLore(Arrays.asList("§8§m----------------------",
									"§7Health §6" + (int) target.getHealth() + "§8/§7" + (int) target.getMaxHealth(),
									"§7Hunger §6" + target.getFoodLevel() + "§8/§720", "§7Position §6X§8/§6Y§8/§6Z§f:",
									" §8- §6X§f" + (int) target.getLocation().getX(),
									" §8- §6Y§f" + (int) target.getLocation().getY(),
									" §8- §6Z§f" + (int) target.getLocation().getZ(), "§8§m----------------------"));
							tskull.setItemMeta(tskullMeta);
							inv.setItem(8, tskull);

							ItemStack decoy = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
							ItemMeta decoyMeta = decoy.getItemMeta();
							decoyMeta.setDisplayName(" ");
							decoy.setItemMeta(decoyMeta);

							inv.setItem(9, decoy);
							inv.setItem(10, decoy);
							inv.setItem(11, decoy);
							inv.setItem(12, decoy);
							inv.setItem(13, decoy);
							inv.setItem(14, decoy);
							inv.setItem(15, decoy);
							inv.setItem(16, decoy);
							inv.setItem(17, decoy);

							int invpos = 18;
							for (int i = 0; i < target.getInventory().getContents().length; i++) {
								inv.setItem(invpos, target.getInventory().getItem(i));
								invpos++;
							}

							p.openInventory(inv);
							p.sendMessage(Main.pl.getConfig().getString("Messages section.Inventory see.opening_message")
									.replace("&", "§").replace("%target%", target.getName()));
						}
					}
				}
			}
		}
	}
}
