package systems.darkcode.AintiXray.main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import systems.darkcode.AintiXray.data.PlayerData;
import systems.darkcode.AintiXray.events.Diamond;
import systems.darkcode.AintiXray.events.Emerald;

public class MainAXR extends JavaPlugin 
{

	public static MainAXR plugin;

	public static Map<UUID, PlayerData> PlayerDataSave = new HashMap<>();
	
	public static String prefix = "";
	
	@Override
	public void onEnable() 
	{
		plugin = this;
		
		reloadConfig();
		FileConfiguration config = getConfig();
		config.options().copyDefaults(true);
		saveDefaultConfig();
		
		prefix = plugin.getConfig().getString("prefix");
		prefix = prefix.replace("&", "§");

		Bukkit.getPluginManager().registerEvents(new Emerald(), plugin);
		Bukkit.getPluginManager().registerEvents(new Diamond(), plugin);
	}
	
	public static MainAXR GetInstance() 
	{
		return plugin;
	}
	
	@Override
	public void onDisable() 
	{
		
	}
}
