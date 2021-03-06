package com.github.drepic26.couponcodes.bukkit.entity;

import org.bukkit.inventory.ItemStack;

import com.github.drepic26.couponcodes.bukkit.BukkitPlugin;
import com.github.drepic26.couponcodes.core.entity.Player;
import com.github.drepic26.couponcodes.core.util.Color;

public class BukkitPlayer extends Player {

	private final BukkitPlugin plugin;
	private final org.bukkit.entity.Player bukkitPlayer;

	public BukkitPlayer(BukkitPlugin plugin, org.bukkit.entity.Player bukkitPlayer) {
		this.plugin = plugin;
		this.bukkitPlayer = bukkitPlayer;
	}

	@Override
	public void sendMessage(String message) {
		for (String line : message.split("\n")) {
			bukkitPlayer.sendMessage(Color.replaceColors(line));
		}
	}

	@Override
	public String getName() {
		return bukkitPlayer.getName();
	}

	@Override
	public void setLevel(int level) {
		bukkitPlayer.setLevel(level);
	}

	@Override
	public int getLevel() {
		return bukkitPlayer.getLevel();
	}

	@Override
	public void giveItem(int item, int amount) {
		if (bukkitPlayer.getInventory().firstEmpty() == -1) {
			bukkitPlayer.getLocation().getWorld().dropItem(bukkitPlayer.getLocation(), new ItemStack(item, amount));
		} else {
			bukkitPlayer.getInventory().addItem(new ItemStack(item, amount));
		}
	}

	public org.bukkit.entity.Player getBukkitPlayer() {
		return bukkitPlayer;
	}

	public BukkitPlugin getPlugin() {
		return plugin;
	}

}
