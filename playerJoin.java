package events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import commands.Modmode;

public class playerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (Player online : Bukkit.getOnlinePlayers()) {
			if (Modmode.modlist.contains(online.getName())) {
				if (!online.hasPermission("modmode.vanish.bypass")) {
					p.hidePlayer(online);
				}
			}
		}
	}
}
