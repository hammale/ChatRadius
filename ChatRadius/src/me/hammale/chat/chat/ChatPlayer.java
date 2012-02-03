package me.hammale.chat.chat;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatPlayer extends PlayerListener {

	 public final chat plugin;
	 
	 public ChatPlayer(chat plugin){
		 this.plugin = plugin;
	 }
	 
	 public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
			 String[] split = e.getMessage().split(" ");
			 if (split.length < 1) return;
			 String cmd = split[0].trim().substring(1).toLowerCase();
			 if (cmd.equalsIgnoreCase("me")) {
				 String message = e.getMessage().replace("/me", "");
				 e.setCancelled(true);
				 for(Player p : plugin.getServer().getOnlinePlayers()){
					 if(p.getWorld() == e.getPlayer().getWorld()){
						 if(checkPlayer(e.getPlayer(), p, e.getPlayer().getWorld()) == true){
							 p.sendMessage("* " + e.getPlayer().getName() + message);
						 }
					 }
				 }
			 }

		 }
	 
	 public void onPlayerChat(PlayerChatEvent e){
		 e.setCancelled(true);
		 String message = e.getMessage();
		 for(Player p : plugin.getServer().getOnlinePlayers()){
			 if(p.getWorld() == e.getPlayer().getWorld()){
				 if(checkPlayer(e.getPlayer(), p, e.getPlayer().getWorld()) == true){
					 p.sendMessage("<" + e.getPlayer().getName() + "> " + message);
				 }
			 }
		 }
	 }
	 public boolean checkPlayer(Player p1, Player p2, World w){
		 
			 double dx = p1.getLocation().getX();
			 double dz = p1.getLocation().getZ();

			 int x = (int)dx;
			 int z = (int)dz;

			 double dx2 = p2.getLocation().getX();
			 double dz2 = p2.getLocation().getZ();

			 int x2 = (int)dx2;
			 int z2 = (int)dz2;

			 Location l1 = w.getBlockAt(x, 127, z).getLocation();
			 Location l2 = w.getBlockAt(x2, 127, z2).getLocation();
			 int dis = (int) l1.distance(l2);

			 if(dis > plugin.getRadius()){
				 return false;
			 }else{
				 return true;
			 }
	 }
}	 