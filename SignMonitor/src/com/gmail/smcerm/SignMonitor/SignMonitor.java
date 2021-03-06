package com.gmail.smcerm.SignMonitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SignMonitor extends JavaPlugin implements Listener {
	public void onEnable(){
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
		this.saveResource("signs.txt", false);
		File players = new File("plugins/SignMonitor/players");
		if (!players.exists()){
			players.mkdir();
		}
		getLogger().info("SignMonitor has been enabled.");
	}

	public void onDisable(){
		getLogger().info("SignMonitor has been disabled.");
	}

	@EventHandler
	public void onSignPlace(SignChangeEvent event) throws IOException{
		Player player = event.getPlayer();
		String[] text = event.getLines();
		Location loc = event.getBlock().getLocation();
		
		/* Log sign changes */
		writeToFile("signs.txt", "============================================================================\nUser: " + 
				player.getName() + "\nLocation: " + "(" + String.valueOf(loc.getX()) + ", " + String.valueOf(loc.getY()) + 
					", " + String.valueOf(loc.getZ()) + ")\n\n" + text[0] + "\n" + text[1] + "\n" + text[2] + "\n" + text[3] + "\n\n");
		
		writeToFile("players/" + player.getName() + ".txt", "============================================================================\n" +
				"Location: " + "(" + String.valueOf(loc.getX()) + ", " + String.valueOf(loc.getY()) + 
					", " + String.valueOf(loc.getZ()) + ")\n\n" + text[0] + "\n" + text[1] + "\n" + text[2] + "\n" + text[3] + "\n\n");
	}
	
	
	public static void writeToFile(String fileName, String text) throws IOException{
		FileWriter writer = new FileWriter("plugins/SignMonitor/" + fileName, true);
		writer.write(text);
		writer.close();
	}
}
