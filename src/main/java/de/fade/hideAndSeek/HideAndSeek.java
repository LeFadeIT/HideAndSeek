package de.fade.hideAndSeek;


import de.fade.hideAndSeek.Events.OnPlayerJoinAndLeaveEvent;
import de.fade.hideAndSeek.Gamestates.GameStateManager;
import de.fade.hideAndSeek.commands.ChangeGameStateCommand;
import de.fade.hideAndSeek.commands.HelloCommand;
import de.fade.hideAndSeek.commands.setLobbySpawn;
import de.fade.hideAndSeek.commands.setSpawn;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideAndSeek extends JavaPlugin {


    private static GameStateManager manager = new GameStateManager();
    private static HideAndSeek instance;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        manager = new GameStateManager();

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(HelloCommand.HelloCommand().build());
            commands.registrar().register(ChangeGameStateCommand.ChangeGameStateCommand().build());
            commands.registrar().register(setLobbySpawn.setSpawnCommand().build());
            commands.registrar().register(setSpawn.setSpawnCommand().build());
        });

        Bukkit.getConsoleSender().sendMessage(
                Component.text("Hide and Seek enabled!")
                        .color(NamedTextColor.AQUA)
        );
        Bukkit.getPluginManager().registerEvents(new OnPlayerJoinAndLeaveEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GameStateManager getGameStateManager() {
        return manager;
    }

    public static HideAndSeek getInstance() {
        return instance;
    }
}
