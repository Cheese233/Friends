package com.prmol.friend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DataGetter {
	JavaPlugin plugin;

	public DataGetter(JavaPlugin plugin) {
		this.plugin = plugin;

	}

	public boolean isExistsFile(String name) {
		File playerDataConfigFile = new File("plugins/Friends/playersdata",
				name);
		if (!playerDataConfigFile.getParentFile().exists()) {
			playerDataConfigFile.getParentFile().mkdir();
		}
		if (playerDataConfigFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean createPlayerDataFile(String name,
			YamlConfiguration configuration, PlayerData playerData) {
		File playerDataConfigFile = new File("plugins/Friends/playersdata",
				name);
		if (!playerDataConfigFile.getParentFile().exists()) {
			playerDataConfigFile.getParentFile().mkdir();
		}
		if (playerDataConfigFile.exists()) {
			return false;
		}
		try {
			playerDataConfigFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		configuration = YamlConfiguration
				.loadConfiguration(playerDataConfigFile);
		configuration.set("friends", playerData.friends);
		try {
			configuration.save(playerDataConfigFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public PlayerData getPlayerData(String name, boolean isCreateWhenNotExists) {
		PlayerData playerData = null;
		YamlConfiguration playerDataConfiguration;
		File playerDataConfigurationFile = new File(
				"plugins/Friends/playersdata", name);
		if (!playerDataConfigurationFile.getParentFile().exists()) {
			playerDataConfigurationFile.getParentFile().mkdir();
		}
		if (!playerDataConfigurationFile.exists()) {
			if (isCreateWhenNotExists) {
				try {
					playerDataConfigurationFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				return null;
			}
		} else {
			playerDataConfiguration = YamlConfiguration
					.loadConfiguration(playerDataConfigurationFile);
			if (playerDataConfiguration.getList("friends") == null) {
				playerDataConfiguration.set("friends", new ArrayList<>());
			}
			List<String> friends = playerDataConfiguration
					.getStringList("friends");
			playerData = new PlayerData(name, friends);

		}

		return playerData;
	}
}
