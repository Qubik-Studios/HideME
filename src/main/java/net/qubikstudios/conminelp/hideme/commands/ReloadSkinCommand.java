package net.qubikstudios.conminelp.hideme.commands;

import net.kyori.adventure.text.Component;
import net.qubikstudios.conminelp.hideme.utils.SkinReload;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadSkinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §cThis player is currently not Online!")); // Error
                return true;
            }
            sender.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6The Skin from §a" + player.getName() + " §6got Reloaded!"));
            SkinReload.reloadSkin(player);
            return true;

        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(Component.text("§8§l[§9HIDE§b§lme§r§8§l] §6Your Skin got Reloaded!"));
            SkinReload.reloadSkin(player);
            return true;
        }
        return false;
    }
}
