package com.gmail.smcerm.SignMonitor;

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
		writeToFile("signs.txt", player.getName() + " placed a sign at " + "(" + String.valueOf(loc.getX()) + ", " + String.valueOf(loc.getY()) + 
			", " + String.valueOf(loc.getZ()) + ") with the text: \n" + text[0] + "\n" + text[1] + "\n" + text[2] + "\n" + text[3] + "\n\n");
	}
	
	/* Log sign changes */
	public static void writeToFile(String fileName, String text) throws IOException{
		FileWriter writer = new FileWriter("plugins/SignMonitor/" + fileName, true);
		writer.write(text);
		writer.close();
	}
}
