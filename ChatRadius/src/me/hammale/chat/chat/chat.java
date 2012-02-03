package me.hammale.chat.chat;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class chat extends JavaPlugin {

	  Logger log = Logger.getLogger("Minecraft");  
	  public FileConfiguration config;
	  private final ChatPlayer pListener = new ChatPlayer(this);
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[ChatRadius] " + pdfFile.getVersion() + " Enabled!");
		loadConfiguration();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, pListener, Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, pListener, Priority.Normal, this);
	}
	
	@Override
	public void onDisable() {	
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[ChatRadius] " + pdfFile.getVersion() + " Disabled!");	
	}

	public void loadConfiguration(){
	    config = getConfig();
	    config.options().copyDefaults(true); 
	    String path = "ChatRadius";
	    config.addDefault(path, 50);
	    config.options().copyDefaults(true);
	    saveConfig();
	}
	
	public int getRadius(){
	    config = getConfig();
	    int amnt = config.getInt("ChatRadius");
	    return amnt;
	}
	
	  public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args){
			if(cmd.getName().equalsIgnoreCase("cr")){
					if(args.length == 0){
						return false;
					}
					if(args[0].equalsIgnoreCase("reload")){
						reloadConfig();
						if(sender instanceof Player){
							sender.sendMessage(ChatColor.GREEN + "ChatRadius Reloaded!");
							return true;
						}else{
							sender.sendMessage("[ChatRadius] Reloaded!");
							return true;
						}
					}
					return false;
			}
			return false;
	  }
	
}