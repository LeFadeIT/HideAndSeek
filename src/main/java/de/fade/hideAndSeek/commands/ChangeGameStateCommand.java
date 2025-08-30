package de.fade.hideAndSeek.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.fade.hideAndSeek.Gamestates.GameStateManager;
import de.fade.hideAndSeek.Gamestates.Gamestates;
import de.fade.hideAndSeek.HideAndSeek;
import de.fade.hideAndSeek.commands.Suggestions.StateSuggestion;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ChangeGameStateCommand {


    public static LiteralArgumentBuilder<CommandSourceStack> ChangeGameStateCommand() {
        return Commands.literal("setState")
                .requires(sender -> sender.getSender().isOp())
                .then(Commands.argument("state", StringArgumentType.word())
                        .suggests(StateSuggestion.SUGGESTION_PROVIDER))
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();
                    GameStateManager manager = HideAndSeek.getGameStateManager();
                    String stateArg = context.getArgument("state", String.class);

                    if(manager.contains(stateArg) && !stateArg.isEmpty()) {
                        Gamestates newState = Gamestates.valueOf(stateArg);
                        manager.setGameState(newState);
                        sender.sendMessage(Component.text("The game state has been changed to " + stateArg + "!").color(NamedTextColor.GREEN));
                        return 1;
                    }
                    sender.sendMessage(Component.text("Dieses game state existiert leider nicht!").color(NamedTextColor.RED));
                    return 0;

                });
    }
}
