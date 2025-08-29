package de.fade.hideAndSeek.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Title title = Title.title(Component.text("test").,Component.text("test"));

        Player player = event.getPlayer();
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        player.showTitle(title);
    }
}
