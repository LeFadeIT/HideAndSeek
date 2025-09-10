package de.fade.hideAndSeek.Game;

import de.fade.hideAndSeek.Utils.Configfiles;
import de.fade.hideAndSeek.Utils.Teleporter;
import de.fade.hideAndSeek.commands.setLobbySpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;

public class HideAndSeekGame {
    Teleporter teleport = new Teleporter();
    public void start(){
        Bukkit.getOnlinePlayers().forEach(player -> {

            try {
                teleport.teleportTo(setLobbySpawn.file, player,"Spawn","Gamelobby",",");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
