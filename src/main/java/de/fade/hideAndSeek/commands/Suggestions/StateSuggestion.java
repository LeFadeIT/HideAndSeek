package de.fade.hideAndSeek.commands.Suggestions;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public class StateSuggestion {
    public static final SuggestionProvider<CommandSourceStack> SUGGESTION_PROVIDER = (context, builder) -> {
        builder.suggest("LOBBYPHASE");
        builder.suggest("GAMEPHASE");
        builder.suggest("ENDPHASE");
        return builder.buildFuture();
    };
}
