package net.onyrihuhd.jetpacks;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyManager {

	private static HashMap<Player, Boolean> flyList = new HashMap<Player, Boolean>();
	private static HashMap<Player, ParticleTrail> trailList = new HashMap<Player, ParticleTrail>();
	private Jetpacks plugin;

	public FlyManager(Jetpacks jetpacks) {
		this.plugin = jetpacks;
	}

	public Boolean isFlying(Player player) {
		if (flyList.containsKey(player)) {
			return flyList.get(player);
		} else {
			flyList.put(player, false);
			return false;
		}
	}

	public void setFlying(Player player, Boolean bool) {
		if (flyList.containsKey(player)) {
			flyList.remove(player);
		}
		flyList.put(player, bool);
		player.setAllowFlight(bool);
		player.setFlying(bool);
		String endStr = (bool) ? ChatColor.GREEN + "ON" : ChatColor.RED + "OFF";
		player.sendMessage(ChatColor.GRAY + "Jetpack turned " + endStr);
		if (!bool) {
			if (trailList.containsKey(player)) {
				trailList.get(player).stop();
				trailList.remove(player);
			}
		} else {
			if (!player.isOnGround()) {
				setTrail(player, true);
			}
		}
	}

	public void setTrail(Player player, Boolean bool) {
		if (bool) {
			if (!trailList.containsKey(player)) {
				trailList.put(player, new ParticleTrail(plugin, player));
			}
		} else {
			if (trailList.containsKey(player)) {
				trailList.get(player).stop();
				trailList.remove(player);
			}
		}
	}

	public void updateFlying(Player player) {
		new BukkitRunnable() {
			@Override
			public void run() {
				ItemStack is = player.getInventory().getChestplate();
				if (isJetpack(is)) {
					if (!isFlying(player)) {
						setFlying(player, true);
					}
				} else {
					if (isFlying(player)) {
						setFlying(player, false);
					}
				}
			}
		}.runTaskLater(plugin, 1);
	}

	public Boolean isJetpack(ItemStack is) {
		Boolean jet = false;
		if (is == null) {
			return false;
		}
		if (is.getType().toString().endsWith("_CHESTPLATE")) {
			if (is.hasItemMeta()) {
				if (is.getItemMeta().hasLore()) {
					for (String line : is.getItemMeta().getLore()) {
						if (line.contains("Jetpack")) {
							jet = true;
						}
					}
				}
			}
		}
		return jet;
	}
}