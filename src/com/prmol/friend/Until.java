package com.prmol.friend;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Until {
	public void openAddNewFriendPanel(Player playerWhoOpenPanel) {
		Inventory onlinePlayersPanel = Bukkit.createInventory(null, 54,
				StringResource.addNewFriendTitle);
		ItemStack back = new ItemStack(Material.SUGAR_CANE);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName("§a上一页");
		back.setItemMeta(backMeta);

		ItemStack next = new ItemStack(Material.SUGAR_CANE);
		ItemMeta nextMeta = next.getItemMeta();
		nextMeta.setDisplayName("§a下一页");
		next.setItemMeta(nextMeta);
		
		ItemStack split=new ItemStack(Material.STAINED_GLASS_PANE);
		split.setDurability((short)14);

		onlinePlayersPanel.setItem(45, back);
		onlinePlayersPanel.setItem(53, next);
		
		@SuppressWarnings("deprecation")
		Player[] onlinePlayers=Bukkit.getOnlinePlayers();
		
		int index=0;
		for(Player onlinePlayer:onlinePlayers)
		{
		}
		
		
	}

}
