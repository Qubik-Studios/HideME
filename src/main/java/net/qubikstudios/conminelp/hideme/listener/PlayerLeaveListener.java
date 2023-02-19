package net.qubikstudios.conminelp.hideme.listener;

import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.VanishManager;
import net.qubikstudios.conminelp.hideme.VanishPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
    VanishManager vanishManager = VanishPlugin.getInstance().getVanishManager();

    @EventHandler
    public void HandlePlayerLeave(PlayerQuitEvent ev) {
        if (VanishManager.getVanished().contains(ev.getPlayer())) {
            ev.quitMessage(null);
            VanishManager.addToVanishCache(ev.getPlayer().getUniqueId());
            vanishManager.setVanished(ev.getPlayer(), false);
        } else {
            ev.quitMessage(Component.text("§e" + ev.getPlayer().getName() + " left the game"));
        }
    }
}
