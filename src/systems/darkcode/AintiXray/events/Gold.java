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

public class Gold implements Listener
{

	@EventHandler
	public void onMine(BlockBreakEvent event)
	{
		if(event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().name().equals("DEEPSLATE_GOLD_ORE"))
		{
			if(MainAXR.plugin.getConfig().getBoolean("enable-gold"))
			{	
				if(MainAXR.PlayerDataSave.containsKey(event.getPlayer().getUniqueId())) 
				{
					PlayerData pData = MainAXR.PlayerDataSave.get(event.getPlayer().getUniqueId());
					pData.setGold(pData.getGold()+1);

					if(pData.getGold() >= MainAXR.plugin.getConfig().getInt("gold-alert")) 
					{
						AntiXrayEvent myEvent = new AntiXrayEvent(event.getPlayer());
						Bukkit.getServer().getPluginManager().callEvent(myEvent);
						
						for(Player players : Bukkit.getOnlinePlayers())
						{
							if(players.hasPermission("antiXray.alert.gold") || players.hasPermission("antiXray.alert.*"))
							{
								String message = MainAXR.plugin.getConfig().getString("gold-message");
								message = message.replace("&", "§"); message = message.replace("{player}", event.getPlayer().getName()); message = message.replace("{ore}", pData.getGold()+"");
								players.sendMessage(MainAXR.prefix+" "+message);
							}
						}
					}

					GameScheduler.RunGameDelayedTask(new GameRunnable() {
						@Override
						public void run() {
							pData.setGold(pData.getGold()-1);
						}
					}, 20*3600);
				}

				else
				{
					MainAXR.PlayerDataSave.put(event.getPlayer().getUniqueId(), new PlayerData(event.getPlayer(), 0, 0, 1, 0, 0, 0));
					PlayerData pData = MainAXR.PlayerDataSave.get(event.getPlayer().getUniqueId());

					if(pData.getGold() >= MainAXR.plugin.getConfig().getInt("gold-alert")) 
					{
						for(Player players : Bukkit.getOnlinePlayers())
						{
							if(players.hasPermission("antiXray.alert.gold") || players.hasPermission("antiXray.alert.*"))
							{
								String message = MainAXR.plugin.getConfig().getString("gold-message");
								message = message.replace("&", "§"); message = message.replace("{player}", event.getPlayer().getName()); message = message.replace("{ore}", pData.getGold()+"");
								players.sendMessage(MainAXR.prefix+" "+message);
							}
						}
					}

					GameScheduler.RunGameDelayedTask(new GameRunnable() {
						@Override
						public void run() {
							pData.setGold(pData.getGold()-1);
						}
					}, 20*3600);
				}
			}
		}
	}
}
