package de.fade.hideAndSeek;

import com.mojang.brigadier.suggestion.SuggestionProvider;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public class HelloCommandSuggestion {
    public static final SuggestionProvider<CommandSourceStack> SUGGESTION_PROVIDER = (context, builder) -> {
        builder.suggest("HideAndSeek");
        builder.suggest("Seek");
        builder.suggest("Hello");
        return builder.buildFuture();
    };
}
