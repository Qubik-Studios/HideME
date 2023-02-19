package net.qubikstudios.conminelp.hideme.listener;

import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.VanishManager;
import net.qubikstudios.conminelp.hideme.VanishPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    VanishManager vanishManager = VanishPlugin.getInstance().getVanishManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        if (VanishManager.getVanishCache().contains(ev.getPlayer().getUniqueId())) {
            vanishManager.hideAll(ev.getPlayer());
            ev.joinMessage(null);
            VanishManager.removeFromVanishCache(ev.getPlayer().getUniqueId());
            vanishManager.setVanished(ev.getPlayer(), true);

            ev.getPlayer().sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6Your current status is Vanished!"));
        } else {
            vanishManager.hideAll(ev.getPlayer());
            ev.joinMessage(Component.text("§e" + ev.getPlayer().getName() + " joined the game"));
        }
    }
}
