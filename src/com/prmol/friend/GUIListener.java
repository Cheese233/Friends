package com.prmol.friend;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {
	JavaPlugin plugin;
	Inventory mainPanel;

	public GUIListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void openFriendInventory(UUID uuid) {
		if (plugin.getServer().getPlayer(uuid) == null) {
			return;
		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent evt) {
		if (!(evt.getWhoClicked() instanceof Player)) {
			return;
		}
		Player player = (Player) evt.getWhoClicked();
		if (evt.getInventory().getTitle()
				.equalsIgnoreCase(StringResource.mainPanelTitle)) {
			evt.setCancelled(true);
			player.closeInventory();
			player.openInventory(evt.getInventory());

			ItemStack item = evt.getInventory().getItem(evt.getRawSlot());
			if (item == null)
				return;
			if (!item.hasItemMeta())
				return;
			if (!item.getItemMeta().hasDisplayName())
				return;
			if (evt.getRawSlot() >= 0 && evt.getRawSlot() <= 5 * 9 - 1) {
				String name = item.getItemMeta().getDisplayName();
				String[] args = name.split("]");
				player.sendMessage(args[1] + "玩家");
				return;
			} else if (evt.getRawSlot() > 5 * 9 - 1) {
				if (evt.getRawSlot() == 5 * 9 + 4 - 1) {
					player.sendMessage("添加好友");
					return;
				} else if (evt.getRawSlot() == 6 * 9 - 1) {
					player.closeInventory();
					player.sendMessage("§a已经关闭好友面板");
				}
			}
		}
	}
}
