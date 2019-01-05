package net.onyrihuhd.jetpacks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventInventoryInteract implements Listener {

	private FlyManager fm;

	public EventInventoryInteract(FlyManager fm) {
		this.fm = fm;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		fm.updateFlying((Player) event.getWhoClicked());
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		fm.updateFlying(event.getPlayer());
	}
}