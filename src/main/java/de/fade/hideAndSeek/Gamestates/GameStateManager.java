package de.fade.hideAndSeek.Gamestates;

import net.kyori.adventure.text.Component;
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
                Bukkit.broadcast(Component.text("§eDie Lobbyphase hat begonnen!"));

            }
            case GAMEPHASE -> {
                Bukkit.broadcast(Component.text("§aDas Spiel startet jetzt!"));
                // Rollen verteilen (Seeker/Hider), Spieler teleportieren, Countdown starten
            }
            case ENDPHASE -> {
                Bukkit.broadcast(Component.text("§cDas Spiel ist vorbei!"));
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
            case "LOBBYPHASE" -> {return true;}
            case "GAMEPHASE" -> {return true;}
            case "ENDPHASE" -> {return true;}
            default -> {return false;}
        }

    }
}
