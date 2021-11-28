package systems.darkcode.AintiXray.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import systems.darkcode.AintiXray.data.PlayerData;
import systems.darkcode.AintiXray.main.GameRunnable;
import systems.darkcode.AintiXray.main.GameScheduler;
import systems.darkcode.AintiXray.main.MainAXR;
import systems.darkcode.AintiXray.messaging.AntiXrayEvent;

public class Lapis implements Listener
{

	@EventHandler
	public void onMine(BlockBreakEvent event)
	{
		if(event.getBlock().getType().equals(Material.LAPIS_ORE) || event.getBlock().getType().name().equals("DEEPSLATE_LAPIS_ORE"))
		{
			if(MainAXR.plugin.getConfig().getBoolean("enable-lapis"))
			{	
				if(MainAXR.PlayerDataSave.containsKey(event.getPlayer().getUniqueId())) 
				{
					PlayerData pData = MainAXR.PlayerDataSave.get(event.getPlayer().getUniqueId());
					pData.setLapis(pData.getLapis()+1);

					if(pData.getLapis() >= MainAXR.plugin.getConfig().getInt("lapis-alert")) 
					{
						AntiXrayEvent myEvent = new AntiXrayEvent(event.getPlayer());
						Bukkit.getServer().getPluginManager().callEvent(myEvent);
						
						for(Player players : Bukkit.getOnlinePlayers())
						{
							if(players.hasPermission("antiXray.alert.lapis") || players.hasPermission("antiXray.alert.*"))
							{
								String message = MainAXR.plugin.getConfig().getString("lapis-message");
								message = message.replace("&", "§"); message = message.replace("{player}", event.getPlayer().getName()); message = message.replace("{ore}", pData.getLapis()+"");
								players.sendMessage(MainAXR.prefix+" "+message);
							}
						}
					}

					GameScheduler.RunGameDelayedTask(new GameRunnable() {
						@Override
						public void run() {
							pData.setLapis(pData.getLapis()-1);
						}
					}, 20*3600);
				}

				else
				{
					MainAXR.PlayerDataSave.put(event.getPlayer().getUniqueId(), new PlayerData(event.getPlayer(), 0, 0, 0, 1, 0, 0));
					PlayerData pData = MainAXR.PlayerDataSave.get(event.getPlayer().getUniqueId());

					if(pData.getLapis() >= MainAXR.plugin.getConfig().getInt("lapis-alert")) 
					{
						for(Player players : Bukkit.getOnlinePlayers())
						{
							if(players.hasPermission("antiXray.alert.lapis") || players.hasPermission("antiXray.alert.*"))
							{
								String message = MainAXR.plugin.getConfig().getString("lapis-message");
								message = message.replace("&", "§"); message = message.replace("{player}", event.getPlayer().getName()); message = message.replace("{ore}", pData.getLapis()+"");
								players.sendMessage(MainAXR.prefix+" "+message);
							}
						}
					}

					GameScheduler.RunGameDelayedTask(new GameRunnable() {
						@Override
						public void run() {
							pData.setLapis(pData.getLapis()-1);
						}
					}, 20*3600);
				}
			}
		}
	}
}