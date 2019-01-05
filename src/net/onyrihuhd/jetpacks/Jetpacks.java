package net.onyrihuhd.jetpacks;

import org.bukkit.plugin.java.JavaPlugin;

public class Jetpacks extends JavaPlugin {

	FlyManager fm = new FlyManager(this);

	public void onEnable() {
		this.getCommand("jet").setExecutor(new CommandJet());
		getServer().getPluginManager().registerEvents(new EventInventoryInteract(fm), this);
		getServer().getPluginManager().registerEvents(new EventPlayerMove(fm), this);
		getServer().getPluginManager().registerEvents(new EventPlayerToogleFlight(fm), this);
	}
}