package commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import spigot.befrendly.modmode.Main;

public class Modmode implements CommandExecutor {

	public static HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> armour = new HashMap<String, ItemStack[]>();
	
	public static HashMap<String, Location> location = new HashMap<String, Location>();
	public static HashMap<String, GameMode> gamemode = new HashMap<String, GameMode>();
	
	public static ArrayList<String> vanishlist = new ArrayList<String>();
	public static ArrayList<String> modlist = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mod")) {
			if (sender instanceof Player) {

				Player p = (Player) sender;
				if (p.hasPermission("modmode.use")) {
					if (modlist.contains(p.getName())) {
						remove(p);
					} else {
						add(p);
					}
				}
			} else {
				Bukkit.getLogger().info("[MOD] Only players are permitted to /mod");
			}
		}
		return true;
	}

	public static void remove(Player p) {
		modlist.remove(p.getName());
		vanishlist.remove(p.getName());

		p.getInventory().setContents(inventory.get(p.getName()));
		p.getInventory().setArmorContents(armour.get(p.getName()));
		p.setGameMode(gamemode.get(p.getName()));
		p.teleport(location.get(p.getName()));
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.showPlayer(p);
		}
		
		p.setAllowFlight(false);
		p.setFlying(false);
		
		p.sendMessage(Main.pl.getConfig().getString("Toggle section.disable_message").replace("&", "§"));
		
	}

	public static Inventory inv;

	@SuppressWarnings("deprecation")
	public static void add(Player p) {

		modlist.add(p.getName());
		vanishlist.add(p.getName());

		inventory.put(p.getName(), p.getInventory().getContents());
		armour.put(p.getName(), p.getInventory().getArmorContents());
		location.put(p.getName(), p.getLocation());
		gamemode.put(p.getName(), p.getGameMode());
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (!players.hasPermission("modmode.vanish.bypass")) {
				players.hidePlayer(p);
			}
		}
		
		p.setGameMode(GameMode.ADVENTURE);
		p.setAllowFlight(true);
		p.setFlying(true);
		
		p.setHealth(20);
		p.setFoodLevel(20);

		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);

		inv = p.getInventory();

		ItemStack ITEM1 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Inventory user.ITEMID"));
		ItemMeta ITEM1Meta = ITEM1.getItemMeta();
		ITEM1Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Inventory user.NAME").replace("&", "§"));
		ITEM1.setItemMeta(ITEM1Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Inventory user.SLOT"), ITEM1);

		ItemStack ITEM2 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Vanish.Enabled.ITEMID"), 1,
				(short) Main.pl.getConfig().getInt("MOD Items.Vanish.Enabled.SUBID"));
		ItemMeta ITEM2Meta = ITEM2.getItemMeta();
		ITEM2Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Vanish.Enabled.NAME").replace("&", "§"));
		ITEM2.setItemMeta(ITEM2Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Vanish.Enabled.SLOT"), ITEM2);

		ItemStack ITEM3 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Enderchest user.ITEMID"));
		ItemMeta ITEM3Meta = ITEM3.getItemMeta();
		ITEM3Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Enderchest user.NAME").replace("&", "§"));
		ITEM3.setItemMeta(ITEM3Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Enderchest user.SLOT"), ITEM3);

		ItemStack ITEM4 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Follow user.ITEMID"));
		ItemMeta ITEM4Meta = ITEM4.getItemMeta();
		ITEM4Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Follow user.NAME").replace("&", "§"));
		ITEM4.setItemMeta(ITEM4Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Follow user.SLOT"), ITEM4);

		ItemStack ITEM5 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Freeze user.ITEMID"));
		ItemMeta ITEM5Meta = ITEM5.getItemMeta();
		ITEM5Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Freeze user.NAME").replace("&", "§"));
		ITEM5.setItemMeta(ITEM5Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Freeze user.SLOT"), ITEM5);

		ItemStack ITEM6 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Push.ITEMID"));
		ItemMeta ITEM6Meta = ITEM6.getItemMeta();
		ITEM6Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Push.NAME").replace("&", "§"));
		ITEM6.setItemMeta(ITEM6Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Push.SLOT"), ITEM6);

		ItemStack ITEM7 = new ItemStack(Main.pl.getConfig().getInt("MOD Items.Random teleporter.ITEMID"));
		ItemMeta ITEM7Meta = ITEM7.getItemMeta();
		ITEM7Meta.setDisplayName(Main.pl.getConfig().getString("MOD Items.Random teleporter.NAME").replace("&", "§"));
		ITEM7.setItemMeta(ITEM7Meta);
		inv.setItem(Main.pl.getConfig().getInt("MOD Items.Random teleporter.SLOT"), ITEM7);
		
		p.sendMessage(Main.pl.getConfig().getString("Toggle section.enable_message").replace("&", "§"));

	}

}
