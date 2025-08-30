package de.fade.hideAndSeek.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.fade.hideAndSeek.commands.Suggestions.HelloCommandSuggestion;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;

public class HelloCommand {
    public static LiteralArgumentBuilder<CommandSourceStack> HelloCommand() {
        return Commands.literal("hello").then(Commands.argument("test", StringArgumentType.greedyString()).suggests(HelloCommandSuggestion.SUGGESTION_PROVIDER)
                .executes(context -> {
                    CommandSender sender = context.getSource().getSender();
                    sender.sendMessage(context.getArgument("test", String.class).replace("&", "§"));
            return 1;
        }));
    }
}
