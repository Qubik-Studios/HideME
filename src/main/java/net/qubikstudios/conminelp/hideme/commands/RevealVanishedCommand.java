package net.qubikstudios.conminelp.hideme.commands;

import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RevealVanishedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            return sendMessage(sender);
        } else if (sender instanceof ConsoleCommandSender) {
            return sendMessage(sender);
        }
        return false;
    }

    private Boolean sendMessage(CommandSender sender) {
        sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6List of Vanished Players [Online]:"));
        if (!VanishManager.getVanished().isEmpty()) {
            StringBuilder onlinePlayer = new StringBuilder();
            onlinePlayer.append("§8§l[§9HIDE§b§lme§r§8§l] §a");
            for (Player p : VanishManager.getVanished()) onlinePlayer.append(p.getName()).append(" ");
            sender.sendMessage(String.valueOf(onlinePlayer));
        } else sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §e§oNo vanished player found"));
        sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6List of Vanished Players [Offline]:"));
        if (!VanishManager.getVanishCache().isEmpty()) {
            StringBuilder offlinePlayer = new StringBuilder();
            offlinePlayer.append("§8§l[§9HIDE§b§lme§r§8§l] §7");
            for (UUID uuid : VanishManager.getVanishCache())
                offlinePlayer.append(Bukkit.getOfflinePlayer(uuid).getName()).append(" ");
            sender.sendMessage(String.valueOf(offlinePlayer));
        } else sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §e§oNo vanished player found"));
        return true;
    }
}
