package spigot.befrendly.modmode;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Modmode;
import events.BlockBreak;
import events.BlockPlace;
import events.Command;
import events.DealDamage;
import events.DropItem;
import events.FoodChange;
import events.InventoryClick;
import events.Leave;
import events.Movement;
import events.PickupItem;
import events.ReceiveDamage;
import events.playerJoin;
import modules.EnderseeModule;
import modules.FollowModule;
import modules.FreezeModule;
import modules.InvseeModule;
import modules.PushModule;
import modules.RandomTPModule;
import modules.VanishModule;

public class Main extends JavaPlugin {

	public static JavaPlugin pl = null;

	public void onEnable() {
		setupEvenements();
		setupModules();
		setupModmode();
		setupConfig();
		pl = this;
	}

	public static ArrayList<String> frozenlist = new ArrayList<String>();

	private void setupConfig() {
		File configFile = new File(this.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}

	}

	private void setupModmode() {
		getCommand("mod").setExecutor(new Modmode());

	}

	private void setupModules() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new InvseeModule(), this);
		pm.registerEvents(new VanishModule(), this);
		pm.registerEvents(new EnderseeModule(), this);
		pm.registerEvents(new FollowModule(), this);
		pm.registerEvents(new FreezeModule(), this);
		pm.registerEvents(new PushModule(), this);
		pm.registerEvents(new RandomTPModule(), this);

	}

	private void setupEvenements() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new DropItem(), this);
		pm.registerEvents(new PickupItem(), this);
		pm.registerEvents(new ReceiveDamage(), this);
		pm.registerEvents(new DealDamage(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new playerJoin(), this);
		pm.registerEvents(new Leave(), this);
		pm.registerEvents(new Movement(), this);
		pm.registerEvents(new Command(), this);
		pm.registerEvents(new FoodChange(), this);

	}

	public void onDisable() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (Modmode.modlist.contains(players.getName())) {
				Modmode.modlist.remove(players.getName());
				Modmode.vanishlist.remove(players.getName());

				players.getInventory().setContents(Modmode.inventory.get(players.getName()));
				players.getInventory().setArmorContents(Modmode.armour.get(players.getName()));
				players.setGameMode(Modmode.gamemode.get(players.getName()));
				players.teleport(Modmode.location.get(players.getName()));
				
				for (Player normal : Bukkit.getOnlinePlayers()) {
					normal.showPlayer(normal);
				}
				
				players.setAllowFlight(false);
				players.setFlying(false);
				
				players.sendMessage(Main.pl.getConfig().getString("Toggle section.disable_message").replace("&", "§"));
				
			}
		}
	}

}
