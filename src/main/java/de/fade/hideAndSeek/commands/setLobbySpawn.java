package de.fade.hideAndSeek.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.fade.hideAndSeek.Utils.Configfiles;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class setLobbySpawn {

    public static Configfiles file;

    public static LiteralArgumentBuilder<CommandSourceStack> setSpawnCommand() {
        return Commands.literal("setLobby").executes(context -> {
            CommandSender sender = context.getSource().getSender();
            sender.sendMessage(Component.text("set Lobby Spawn").color(NamedTextColor.GREEN));
            file = new Configfiles("spawns.yml");
            Player player = (Player) context.getSource().getSender();
            String coordinate = player.getX() + "," + player.getY() + "," + player.getZ() + "," + player.getYaw() + "," + player.getPitch();
            try {
                file.writeFile("Spawn","Lobby",coordinate);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return 1;
        });
    }
}
