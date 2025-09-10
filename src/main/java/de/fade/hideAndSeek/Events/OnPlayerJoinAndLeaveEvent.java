package de.fade.hideAndSeek.Events;

import de.fade.hideAndSeek.Gamestates.GameStateManager;
import de.fade.hideAndSeek.Gamestates.Gamestates;
import de.fade.hideAndSeek.HideAndSeek;
import de.fade.hideAndSeek.Timer.LobbyCountdownManager;
import de.fade.hideAndSeek.Utils.Configfiles;
import de.fade.hideAndSeek.Utils.Teleporter;
import de.fade.hideAndSeek.commands.setLobbySpawn;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class OnPlayerJoinAndLeaveEvent implements Listener {

    private final GameStateManager manager = HideAndSeek.getGameStateManager();
    private static JavaPlugin plugin = HideAndSeek.getInstance();
    public static LobbyCountdownManager lobbyCountdownManager = new LobbyCountdownManager(plugin, 1);
    Teleporter teleporter = new Teleporter();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        Title title = Title.title(Component.text(player.getName() + " Welcome to").color(TextColor.color(0, 215, 255)), Component.text("Hide And Seek!").color(TextColor.color(252, 255, 80)));
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        player.showTitle(title);
        event.joinMessage(Component.text("[+] " + player.getName() + " joined!"));
        lobbyCountdownManager.checkAndStartCountdown(manager);
        Configfiles file = setLobbySpawn.file;
        if (GameStateManager.getCurrentState() == Gamestates.LOBBYPHASE) {
            teleporter.teleportTo(file, player, "Spawn", "Lobby",",");
        }else {
            teleporter.teleportToSpec(file, player);
        }


    }



    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.quitMessage(null);
        lobbyCountdownManager.checkAndStartCountdown(manager);
    }



}
