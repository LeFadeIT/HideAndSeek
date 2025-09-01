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
                        .suggests(StateSuggestion.SUGGESTION_PROVIDER)
                        .executes(context -> {
                            CommandSender sender = context.getSource().getSender();
                            GameStateManager manager = HideAndSeek.getGameStateManager();
                            String stateArg = context.getArgument("state", String.class);
                            Gamestates newState = Gamestates.LOBBYPHASE;

                            if (manager.contains(stateArg)) {
                                newState = Gamestates.valueOf(stateArg);
                            }

                            if (manager.getCurrentState().equals(newState) && !stateArg.isEmpty()) {
                                sender.sendMessage(Component.text("Das Spiel befindet sich schon in diesem State!").color(NamedTextColor.BLUE));
                                return 1;
                            }
                            if (manager.contains(stateArg)) {

                                manager.setGameState(newState);
                                sender.sendMessage(Component.text("Das Game state wurde zu " + stateArg + " geändert!").color(NamedTextColor.GREEN));
                                return 1;
                            }
                            sender.sendMessage(Component.text("Dieses game state existiert leider nicht!").color(NamedTextColor.RED));
                            return 0;

                        }));
    }
}
