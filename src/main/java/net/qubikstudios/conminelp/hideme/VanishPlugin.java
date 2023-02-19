package net.qubikstudios.conminelp.hideme;

import net.qubikstudios.conminelp.hideme.commands.ReloadSkinCommand;
import net.qubikstudios.conminelp.hideme.commands.RevealVanishedCommand;
import net.qubikstudios.conminelp.hideme.commands.VanishCommand;
import net.qubikstudios.conminelp.hideme.listener.PlayerJoinListener;
import net.qubikstudios.conminelp.hideme.listener.PlayerLeaveListener;
import net.qubikstudios.conminelp.hideme.utils.MessageTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VanishPlugin extends JavaPlugin {
    private static VanishPlugin instance;
    private VanishManager vanishManager;
    private MessageTask messageTask;

    @Override
    public void onEnable() {
        instance = this;
        this.vanishManager = new VanishManager(instance);

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), instance);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), instance);

        Objects.requireNonNull(getCommand("vanish")).setExecutor(new VanishCommand());
        Objects.requireNonNull(getCommand("reloadskin")).setExecutor(new ReloadSkinCommand());
        Objects.requireNonNull(getCommand("revealvanished")).setExecutor(new RevealVanishedCommand());

        messageTask = new MessageTask(instance);
    }

    @Override
    public void onDisable() {
        messageTask.stopTask();
    }

    public static VanishPlugin getInstance() {
        return instance;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

}
