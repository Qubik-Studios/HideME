package net.qubikstudios.conminelp.hideme;

import net.qubikstudios.conminelp.hideme.utils.SkinReload;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {
    private final Plugin plugin;
    private static List<Player> vanished;

    private static List<UUID> vanishCache;


    public VanishManager(Plugin plugin) {
        this.plugin = plugin;
        vanished = new ArrayList<>();
        vanishCache = new ArrayList<>();
    }

    public static List<Player> getVanished() {
        return vanished;
    }

    public static List<UUID> getVanishCache() {
        return vanishCache;
    }

    public static void addToVanishCache(UUID playerUUID) {
        if (vanishCache.contains(playerUUID)) return;
        vanishCache.add(playerUUID);
    }

    public static void removeFromVanishCache(UUID playerUUID) {
        vanishCache.remove(playerUUID);
    }

    public boolean isVanished(Player player) {
        return vanished.contains(player);
    }

    public void setVanished(Player player, boolean bool) {
        if (bool) {
            vanished.add(player);
            setSkin(player);
        } else vanished.remove(player);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) continue;
            if (bool) hide(player, onlinePlayer);
            else show(player, onlinePlayer);

        }
    }

    private void hide(Player player, Player onlinePlayer) {
        onlinePlayer.hidePlayer(plugin, player);
        setSkin(player);
    }

    private void show(Player player, Player onlinePlayer) {
        onlinePlayer.showPlayer(plugin, player);
    }

    public void hideAll(Player player) {
        vanished.forEach(player1 -> player.hidePlayer(plugin, player1));
    }

    public void setSkin(Player player) {
        SkinReload.reloadSkin(player);
    }

}
