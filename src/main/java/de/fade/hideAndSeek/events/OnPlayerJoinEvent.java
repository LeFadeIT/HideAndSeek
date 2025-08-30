package de.fade.hideAndSeek.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Title title = Title.title(Component.text(player.getName() + " Welcome to").color(TextColor.color(0, 215, 255)),Component.text("Hide And Seek!").color(TextColor.color(252, 255, 80)));
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        player.showTitle(title);
        event.joinMessage(Component.text("§7[§a+§7] §f" + player.getName() + " joined!"));
    }
}
