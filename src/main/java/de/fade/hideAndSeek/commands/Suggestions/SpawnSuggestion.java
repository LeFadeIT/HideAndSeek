package de.fade.hideAndSeek.commands.Suggestions;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public class SpawnSuggestion {
    private static int maxplayer = 10;

    public static final SuggestionProvider<CommandSourceStack> SUGGESTION_PROVIDER = (context, builder) -> {
        for (int i = 0; i < maxplayer; i++){
            builder.suggest(i);
        }
        return builder.buildFuture();
    };
}
