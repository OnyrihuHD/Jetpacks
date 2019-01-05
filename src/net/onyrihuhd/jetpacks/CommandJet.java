package net.onyrihuhd.jetpacks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandJet implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			ItemStack jet = new ItemStack(Material.LEATHER_CHESTPLATE);
			ItemMeta im = jet.getItemMeta();
			List<String> lore = new ArrayList<>();
			lore.add(ChatColor.GRAY + "Jetpack");
			im.setLore(lore);
			im.setDisplayName(ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Jetpack Lv1");
			im.setUnbreakable(true);
			jet.setItemMeta(im);

			player.getInventory().addItem(jet);
		} else {
			sender.sendMessage("ERROR : Can be used by players only");
		}
		return true;
	}
}