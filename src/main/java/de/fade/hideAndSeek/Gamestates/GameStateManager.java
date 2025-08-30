package de.fade.hideAndSeek.Gamestates;

import de.fade.hideAndSeek.Timer.Countdown;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class GameStateManager {

    private Gamestates currentState;

    public GameStateManager() {
        this.currentState = Gamestates.LOBBYPHASE; // Startzustand
    }

    public Gamestates getCurrentState() {
        return currentState;
    }

    public void setGameState(Gamestates newState) {
        if (this.currentState == newState) return; // kein Wechsel nötig

        // Optional: Aktionen beim Verlassen
        onExit(this.currentState);

        this.currentState = newState;

        // Aktionen beim Betreten
        onEnter(this.currentState);
    }

    private void onEnter(Gamestates state) {
        switch (state) {
            case LOBBYPHASE -> {
                Bukkit.broadcast(Component.text("Die Lobbyphase hat begonnen!").color(NamedTextColor.YELLOW));

            }
            case GAMEPHASE -> {
                Bukkit.broadcast(Component.text("Das Spiel startet jetzt!").color(NamedTextColor.GREEN));
                // Rollen verteilen (Seeker/Hider), Spieler teleportieren, Countdown starten
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
                // z. B. Countdown stoppen
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
