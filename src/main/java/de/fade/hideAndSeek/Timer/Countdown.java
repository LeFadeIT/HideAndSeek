package de.fade.hideAndSeek.Timer;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class Countdown {

    private final JavaPlugin plugin;
    private final int startSeconds;
    private final Consumer<Integer> onTick;   // Wird bei jedem Tick aufgerufen
    private final Runnable onEnd;             // Wird am Ende aufgerufen

    private int secondsLeft;

    public Countdown(JavaPlugin plugin, int startSeconds, Consumer<Integer> onTick, Runnable onEnd) {
        this.plugin = plugin;
        this.startSeconds = startSeconds;
        this.secondsLeft = startSeconds;
        this.onTick = onTick;
        this.onEnd = onEnd;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (secondsLeft <= 0) {
                    if (onEnd != null) onEnd.run();
                    cancel();
                    return;
                }

                if (onTick != null) onTick.accept(secondsLeft);

                secondsLeft--;
            }
        }.runTaskTimer(plugin, 0L, 20L); // 20 ticks = 1 Sekunde
    }

    public void restart(int  seconds) {
        secondsLeft = seconds;
    }
    public void stop() {
        secondsLeft = 0;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}
