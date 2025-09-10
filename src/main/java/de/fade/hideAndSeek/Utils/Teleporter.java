package de.fade.hideAndSeek.Utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Teleporter {
    public void teleportTo(Configfiles file, Player player, String cat, String key, String splitby) throws IOException {
        if (file != null) {
            String[] parts = file.readFile(cat, key, splitby);
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);
            float yaw = Float.parseFloat(parts[3]);
            float pitch = Float.parseFloat(parts[4]);
            Location location = new Location(player.getWorld(), x, y, z, yaw, pitch);
            player.teleport(location);
        } else {
            Location location = new Location(player.getWorld(), 238 ,65,-394);
            player.teleport(location);

        }
    }
    public void teleportToSpec(Configfiles file, Player player) {
        player.sendMessage(Component.text("Test"));
    }
}
