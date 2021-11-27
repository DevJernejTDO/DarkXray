package systems.darkcode.AintiXray.main;

import org.bukkit.Bukkit;

public abstract class GameRunnable implements Runnable {

	int runnableID;
	MainAXR plugin;
	
	public void SetID(int id) {
		this.runnableID = id;
	}
	
	public void CancelTask() {
		Bukkit.getScheduler().cancelTask(this.runnableID);
	}

	public void SetPlugin(MainAXR plugin) {
		this.plugin = plugin;
	}
	
	public int GetRunnableID() {
		return this.runnableID;
	}
	
	public MainAXR GetPlugin() {
		return this.plugin;
	}
	
}
