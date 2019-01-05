package net.onyrihuhd.jetpacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventPlayerMove implements Listener {

	private FlyManager fm;

	public EventPlayerMove(FlyManager fm) {
		this.fm = fm;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (fm.isFlying(player) && !player.isFlying()) {
			if (player.getVelocity().getY() < -0.7) {
				player.setVelocity(player.getVelocity().setY(-0.6));
				player.setGravity(false);
				fm.setTrail(player, true);
			}
			if (player.isOnGround()) {
				player.setGravity(true);
				fm.setTrail(player, false);
			}
		} else {
			if (!player.hasGravity()) {
				player.setGravity(true);
				if (!player.isFlying()) {
					fm.setTrail(player, false);
				}
			}
		}
	}
}