package de.fade.hideAndSeek.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import net.kyori.adventure.text.Component;

import java.util.function.Supplier;

public class ActionBarDisplay {

    private final JavaPlugin plugin;
    private final Supplier<Component> messageSupplier; // Liefert dynamische Nachrichten
    private final long intervalTicks;
    private BukkitRunnable task;

    public ActionBarDisplay(JavaPlugin plugin, Supplier<Component> messageSupplier, long intervalTicks) {
        this.plugin = plugin;
        this.messageSupplier = messageSupplier;
        this.intervalTicks = intervalTicks;
    }

    /**
     * Startet die wiederholte Anzeige
     */
    public void start() {
        if (task != null) return; // Schon aktiv

        task = new BukkitRunnable() {
            @Override
            public void run() {
                Component message = messageSupplier.get();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(message);
                }
            }
        };

        task.runTaskTimer(plugin, 0L, intervalTicks);
    }

    /**
     * Stoppt die Anzeige
     */
    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
