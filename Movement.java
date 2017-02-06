package events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import spigot.befrendly.modmode.Main;

public class Movement implements Listener {

	@EventHandler
	public void onMovement(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (Main.frozenlist.contains(p.getName())) {
			e.setTo(e.getFrom());
		}
	}
}
