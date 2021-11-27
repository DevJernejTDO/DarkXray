package systems.darkcode.AintiXray.messaging;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import systems.darkcode.AintiXray.data.PlayerData;
import systems.darkcode.AintiXray.main.MainAXR;

public class AntiXrayEvent extends Event 
{
	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private int emerald;
	private int diamond;
	private int gold;
	private int iron;
	private int lapis;
	private int netherite;

	public AntiXrayEvent(Player user) 
	{
		PlayerData pData = MainAXR.PlayerDataSave.get(user.getUniqueId());
		player = user;
		emerald = pData.getEmeralds();
		diamond = pData.getDiamonds();
		gold = pData.getGold();
		iron = pData.getIron();
		lapis = pData.getLapis();
		netherite = pData.getNetherite();

	}

	public Player getPlayer() 
	{
		return player;
	}
	
	public int getEmeralds() 
	{
		return emerald;
	}
	
	public int getDiamonds() 
	{
		return diamond;
	}

	public int getGold() 
	{
		return gold;
	}
	
	public int getIron()
	{
		return iron;
	}
	
	public int getLapis() 
	{
		return lapis;
	}
	
	public int getNetherite()
	{
		return netherite;
	}
	
	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}
}
