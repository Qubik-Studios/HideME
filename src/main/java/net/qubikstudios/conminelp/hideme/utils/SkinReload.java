package net.qubikstudios.conminelp.hideme.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;

public class SkinReload {

    public static void reloadSkin(Player player) {
        PlayerProfile profile = player.getPlayerProfile();
        PlayerTextures skin = profile.getTextures();
        profile.setTextures(skin);
    }
}
