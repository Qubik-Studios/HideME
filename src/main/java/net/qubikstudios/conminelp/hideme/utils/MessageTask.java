package net.qubikstudios.conminelp.hideme.utils;


import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.VanishManager;
import net.qubikstudios.conminelp.hideme.VanishPlugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class MessageTask {

    private final VanishPlugin plugin;
    private final BukkitTask task;

    public MessageTask(VanishPlugin plugin) {
        this.plugin = plugin;
        this.task = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : VanishManager.getVanished()) {
                    p.sendActionBar(Component.text("§cYou're vanished"));
                }
            }
        }, 0, 20);
    }

    public void stopTask() {
        task.cancel();
    }
}
