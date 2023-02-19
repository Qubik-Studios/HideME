package net.qubikstudios.conminelp.hideme.commands;

import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.VanishManager;
import net.qubikstudios.conminelp.hideme.VanishPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        VanishManager vanishManager = VanishPlugin.getInstance().getVanishManager();
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §cThis player is currently not Online!")); // Error
                return true;
            }
            if (vanishManager.isVanished(player)) {
                vanishManager.setVanished(player, false);
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6The player §a" + player.getName() + " §6is now no longer in vanish!")); //Show
                Bukkit.broadcast(Component.text("§e" + player.getName() + " joined the game"));
            } else {
                vanishManager.setVanished(player, true);
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6The player §a" + player.getName() + " §6is now in vanish!")); //Hide
                Bukkit.broadcast(Component.text("§e" + player.getName() + " left the game"));
            }
            return true;

        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            if (vanishManager.isVanished(player)) {
                vanishManager.setVanished(player, false);
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6You are now no longer in vanish!")); //Show yourself
                Bukkit.broadcast(Component.text("§e" + player.getName() + " joined the game"));
            } else {
                vanishManager.setVanished(player, true);
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6You are now in vanish!")); //Hide yourself
                Bukkit.broadcast(Component.text("§e" + player.getName() + " left the game"));

            }
            return true;
        }
        return false;
    }
}
