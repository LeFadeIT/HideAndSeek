package de.fade.hideAndSeek;

import de.fade.hideAndSeek.commands.HelloCommand;
import de.fade.hideAndSeek.events.OnPlayerJoinEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideAndSeek extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(HelloCommand.HelloCommand().build());
        });

        Bukkit.getConsoleSender().sendMessage(Component.text("Hide and Seek enabled!").color(TextColor.color(0, 215, 255)));
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoinEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
