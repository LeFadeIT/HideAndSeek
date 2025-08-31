package de.fade.hideAndSeek.Timer;

import de.fade.hideAndSeek.Gamestates.GameStateManager;
import de.fade.hideAndSeek.Gamestates.Gamestates;
import de.fade.hideAndSeek.Utils.ActionBarDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyCountdownManager {

    private final JavaPlugin plugin;
    private final int requiredPlayers;
    private Countdown countdown;
    private ActionBarDisplay actionBarDisplay;

    public LobbyCountdownManager(JavaPlugin plugin, int requiredPlayers) {
        this.plugin = plugin;
        this.requiredPlayers = requiredPlayers;

        // ActionBar initialisieren, aber noch nicht starten
        this.actionBarDisplay = new ActionBarDisplay(plugin, this::getLobbyStatusMessage, 20);
        this.actionBarDisplay.start(); // startet dauerhafte Anzeige
    }

    // Liefert die aktuelle Lobby-Statusnachricht für ActionBar
    private Component getLobbyStatusMessage() {
        int online = Bukkit.getOnlinePlayers().size();

        if (countdown != null) {
            return Component.text("Countdown läuft: " + countdown.getSecondsLeft() + " Sekunden", NamedTextColor.GREEN);
        }

        if (online >= requiredPlayers) {
            return Component.text("Genug Spieler online! Countdown startet gleich...", NamedTextColor.YELLOW);
        } else {
            return Component.text("Mindestens " + requiredPlayers + " Spieler nötig, aktuell: " + online, NamedTextColor.RED);
        }
    }

    public void checkAndStartCountdown(GameStateManager gameStateManager) {
        int online = Bukkit.getOnlinePlayers().size();

        if (online >= requiredPlayers) {
            if (countdown == null) {
                startCountdown(gameStateManager);
            }
        } else {
            if (countdown != null) {
                countdown.stop();
                countdown = null;
                Bukkit.broadcast(Component.text("Countdown gestoppt! Zu wenige Spieler.").color(NamedTextColor.RED));
            }
        }
    }

    private void startCountdown(GameStateManager gameStateManager) {
        countdown = new Countdown(plugin, 10, seconds -> {
            Bukkit.broadcast(Component.text("Spiel startet in " + seconds + " Sekunden").color(NamedTextColor.YELLOW));
        }, () -> {
            gameStateManager.setGameState(Gamestates.GAMEPHASE);
        });

        countdown.start();
        Bukkit.broadcast(Component.text("Countdown gestartet!").color(NamedTextColor.GREEN));
    }
}
