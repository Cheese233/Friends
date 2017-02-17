package com.prmol.friend;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

class CommandListener implements CommandExecutor {
	JavaPlugin plugin;
	GUIManager gm=new GUIManager(plugin);
	public CommandListener(JavaPlugin plugin)
	{
		this.plugin=plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (label.equalsIgnoreCase("friend")
				|| label.equalsIgnoreCase("friends")
				|| label.equalsIgnoreCase("f")) {
			if(args.length!=0)
			{
				if(args.length!=1)
				{
					return false;
				}
				if(args.length==1)
				{
					if(!args[0].equalsIgnoreCase("help"))
					{
						return false;
					}else
					{
						sender.sendMessage("§a>>>>>>>>>>>> Friends 插件 <<<<<<<<<<<<<<<");
						sender.sendMessage("§a>>>> §d怪美异元§a独创");
						sender.sendMessage("§a>>>> §c盗版必究");
						sender.sendMessage("§e /f 或 /friend 或 /friends §a直接打开面板 ");
						sender.sendMessage("§a>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> <<");
						return true;
					}
					
				}
			}
			if(!(sender instanceof Player))
			{
				sender.sendMessage("操你妈不是玩家玩你妈逼朋友插件");
				return true;
			}
			Player player=(Player)sender;
			
			player.playSound(player.getLocation(), Sound.NOTE_PIANO, 0, 0);
			
			gm.openFriendPanel(player);
			return true;
		}
		return false;
	}
}
