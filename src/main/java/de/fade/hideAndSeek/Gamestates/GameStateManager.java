package de.fade.hideAndSeek.Gamestates;

import de.fade.hideAndSeek.Events.OnPlayerJoinAndLeaveEvent;
import de.fade.hideAndSeek.Game.HideAndSeekGame;
import de.fade.hideAndSeek.HideAndSeek;
import de.fade.hideAndSeek.Timer.Countdown;
import de.fade.hideAndSeek.Timer.LobbyCountdownManager;
import de.fade.hideAndSeek.Utils.ActionBarDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.checkerframework.checker.units.qual.A;

public class GameStateManager {

    private static Gamestates currentState;

    public GameStateManager() {
        currentState = Gamestates.LOBBYPHASE; // Startzustand
    }

    public static Gamestates getCurrentState() {
        return currentState;
    }

    public void setGameState(Gamestates newState) {
        if (currentState == newState) return; // kein Wechsel nötig

        // Optional: Aktionen beim Verlassen
        onExit(currentState);

        currentState = newState;

        // Aktionen beim Betreten
        onEnter(currentState);
    }

    private void onEnter(Gamestates state) {
        switch (state) {
            case LOBBYPHASE -> {
                Bukkit.broadcast(Component.text("Die Lobbyphase hat begonnen!").color(NamedTextColor.YELLOW));
                LobbyCountdownManager.actionBarDisplay.start();
                LobbyCountdownManager.countdown.restart(10);
                LobbyCountdownManager.countdown.start();

            }
            case GAMEPHASE -> {
                Bukkit.broadcast(Component.text("Das Spiel startet jetzt!").color(NamedTextColor.GREEN));
                // Rollen verteilen (Seeker/Hider), Spieler teleportieren, Countdown starten
                HideAndSeekGame game = new HideAndSeekGame();
                game.start();
            }
            case ENDPHASE -> {
                Bukkit.broadcast(Component.text("Das Spiel ist vorbei!").color(NamedTextColor.RED));
                // Gewinner anzeigen, Spieler zurück in die Lobby setzen
            }
        }
    }

    private void onExit(Gamestates state) {
        switch (state) {
            case LOBBYPHASE -> {
                LobbyCountdownManager.countdown.restart(10);
                LobbyCountdownManager.actionBarDisplay.stop();
                LobbyCountdownManager.countdown.stop();
            }
            case GAMEPHASE -> {
                // z. B. Tasks beenden
            }
            case ENDPHASE -> {
                // Cleanup
            }
        }
    }

    public Boolean contains(String state) {
        switch (state) {
            case "LOBBYPHASE":
            case "GAMEPHASE":
            case "ENDPHASE":
                return true;
            default:
                return false;
        }

    }
}
