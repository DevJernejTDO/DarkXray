package systems.darkcode.AintiXray.main;

import org.bukkit.Bukkit;

public class GameScheduler {

	public static void RunGameRepeatingTask(GameRunnable runnable, int delay, int interval) {
		int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainAXR.GetInstance(), runnable, delay, interval);
		runnable.SetID(id);
		runnable.SetPlugin(MainAXR.GetInstance());
	}

	public static void RunGameDelayedTask(GameRunnable runnable, int delay) {
		int id = Bukkit.getScheduler().scheduleSyncDelayedTask(MainAXR.GetInstance(), runnable, delay);
		runnable.SetID(id);
		runnable.SetPlugin(MainAXR.GetInstance());
	}
	
}
