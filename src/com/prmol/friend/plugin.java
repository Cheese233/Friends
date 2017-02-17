package com.prmol.friend;


import org.bukkit.plugin.java.JavaPlugin;

public class plugin extends JavaPlugin{
	@Override
	public void onEnable() {
		if(this.getDataFolder().exists()==false)
		{
			this.getDataFolder().mkdir();
		}
		this.getServer().getPluginManager().registerEvents(new GUIListener(this),this);
		this.getCommand("friends").setExecutor(new CommandListener(this));
	}
}
