package systems.darkcode.AintiXray.data;

import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerData 
{

	private UUID uuid;
	private String PlayerName;
	private int Emeralds;
	private int Diamonds;
	private int Gold;
	private int Iron;
	private int Lapis;
	private int Netherite;
	
	public PlayerData(Player player, int e, int d, int g, int i, int l, int n)
	{
		this.uuid = player.getUniqueId();
		this.PlayerName = player.getName();
		this.Emeralds = e;
		this.Diamonds = d;
		this.Gold = g;
		this.Iron = i;
		this.Lapis = l;
		this.Netherite = n;
	}
	
	public UUID getUUID() 
	{
		return uuid;
	}
	
	public String getName() 
	{
		return PlayerName;
	}
	
	public int getEmeralds()
	{
		return Emeralds;
	}
	
	public void setEmeralds(int i)
	{
		Emeralds = i;
	}
	
	public int getDiamonds()
	{
		return Diamonds;
	}
	
	public void setDiamonds(int i)
	{
		Diamonds = i;
	}
	
	public int getGold()
	{
		return Gold;
	}
	
	public void setGold(int i)
	{
		Gold = i;
	}
	
	public int getIron()
	{
		return Iron;
	}
	
	public void setIron(int i)
	{
		Iron = i;
	}
	
	public int getLapis()
	{
		return Lapis;
	}
	
	public void setLapis(int i)
	{
		Lapis = i;
	}
	
	public int getNetherite()
	{
		return Netherite;
	}
	
	public void setNetherite(int i)
	{
		Netherite = i;
	}
}
