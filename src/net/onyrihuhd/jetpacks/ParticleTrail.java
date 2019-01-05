package net.onyrihuhd.jetpacks;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ParticleTrail {

	private Jetpacks plugin;
	private int taskID;
	private Boolean even = true;

	public ParticleTrail(Jetpacks jetpacks, Player player) {
		this.plugin = jetpacks;
		taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				Location loc = player.getLocation().add(0, 0.5, 0);
				loc.subtract(loc.getDirection().multiply(1.2));
				player.spawnParticle(Particle.SMOKE_LARGE, loc, 10, 0, 0, 0, 0.05);
				player.spawnParticle(Particle.FLAME, loc, 2, 0, 0, 0, 0.05);
				if (even) {
					player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 1);
				}
				even = !even;
			}
		}, 0L, 4L);
	}

	public void stop() {
		plugin.getServer().getScheduler().cancelTask(taskID);
	}
}