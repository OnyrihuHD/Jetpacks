package net.onyrihuhd.jetpacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class EventPlayerToogleFlight implements Listener {

	private FlyManager fm;

	public EventPlayerToogleFlight(FlyManager fm) {
		this.fm = fm;
	}

	@EventHandler
	public void onFlyChange(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (event.isFlying()) {
			if (fm.isFlying(player)) {
				fm.setTrail(player, true);
			}
		} else {
			fm.setTrail(player, false);
		}
	}
}