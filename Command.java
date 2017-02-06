package events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import commands.Modmode;
import spigot.befrendly.modmode.Main;

public class Command implements Listener {

	@EventHandler
	public void onCommandProcess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (Modmode.modlist.contains(p.getName())) {
			List<String> list = Main.pl.getConfig().getStringList("Blocked Commands");
			for (String l : list) {
				if (e.getMessage().startsWith(l)) {
					e.setCancelled(true);
					p.sendMessage("§6§lMOD §8- §6" + e.getMessage() + " §7is blocked during §6modmode§7.");
				}
			}
		}
	}
}
