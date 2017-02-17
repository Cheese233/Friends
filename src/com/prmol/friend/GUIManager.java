package com.prmol.friend;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIManager {
	JavaPlugin plugin;
	DataGetter dataGetter = new DataGetter(plugin);
	ItemStack close = new ItemStack(Material.REDSTONE_LAMP_OFF);
	ItemStack addNewFriend = new ItemStack(Material.SIGN);

	public void debug(Player player,String message)
	{
		player.sendMessage(message);
	}
	public static ItemStatck getPlayerItem(String playerName)
	{
		ItemStack playerItemStack= null;
		if((Bukkit.getOfflinePlayer(name)
	}
	public GUIManager(JavaPlugin plugin) {
		this.plugin = plugin;

		ItemMeta closeItemMeta = close.getItemMeta();
		closeItemMeta.setDisplayName("§c关闭");
		close.setItemMeta(closeItemMeta);

		ItemMeta addNewFriendsMeta = addNewFriend.getItemMeta();
		addNewFriendsMeta.setDisplayName("§a添加/查找新的朋友");
		addNewFriend.setItemMeta(addNewFriendsMeta);
	}

	public void openFriendPanel(Player player) {
		debug(player,"开始打开界面");
		if (!player.isOnline()) {
			debug(player,"不在线");
			return;
		}
		if (!dataGetter.isExistsFile(player.getName())) {
			debug(player,"没有你的数据啊");
			dataGetter.getPlayerData(player.getName(), true);
			debug(player,"创建好了");
		}
		PlayerData playerData = dataGetter.getPlayerData(player.getName(),
				false);
		if (playerData == null) {
			debug(player,"你的playerdata是null呀");
			return;
		}
		for(String name:playerData.friends)
		{
			player.sendMessage(name);
		}
		Inventory friendInventory = Bukkit.getServer().createInventory(null,
				54, StringResource.mainPanelTitle);
		friendInventory.setItem(5 * 9 + 4 - 1, addNewFriend);
		friendInventory.setItem(6 * 9 - 1, close);
		List<ItemStack> friends = new ArrayList<>();
		if (!playerData.friends.isEmpty()) {
			for (String playerName : playerData.friends) {
				
				ItemStack playerHead = null;
				if (Bukkit.getServer().getPlayer(playerName) == null) {
					playerHead = new ItemStack(Material.SKULL_ITEM, 1,
							(short) SkullType.WITHER.ordinal());
					ItemMeta itemMeta = playerHead.getItemMeta();
					itemMeta.setDisplayName("§8[未知玩家]" + playerName);
					playerHead.setItemMeta(itemMeta);
				} else if (!Bukkit.getServer().getPlayer(playerName).isOnline()) {
					playerHead = new ItemStack(Material.SKULL_ITEM, 1,
							(short) SkullType.SKELETON.ordinal());
					ItemMeta itemMeta = playerHead.getItemMeta();
					itemMeta.setDisplayName("§7[离线]" + playerName);
					playerHead.setItemMeta(itemMeta);
				} else {
					playerHead = new ItemStack(Material.SKULL_ITEM, 1,
							(short) SkullType.PLAYER.ordinal());
					ItemMeta itemMeta = playerHead.getItemMeta();
					itemMeta.setDisplayName("§a[在线]" + playerName);
					playerHead.setItemMeta(itemMeta);
				}
				friends.add(playerHead);
			}
			for (int i = 0; i < friends.size(); i++) {
				ItemStack friend = friends.get(i);
				friendInventory.addItem(friend);
				debug(player,"翻页功能有待开发");
			}
		}else
		{
			debug(player,"我帮你打开了面板，但是你没好友");
		}
		
		player.sendMessage("§a已经打开好友面板.");
		player.openInventory(friendInventory);
	}
}
