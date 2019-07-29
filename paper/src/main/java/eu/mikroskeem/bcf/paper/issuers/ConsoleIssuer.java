package eu.mikroskeem.bcf.paper.issuers;

import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public final class ConsoleIssuer implements PaperIssuer<CommandSender> {
    private final CommandSender base;

    public ConsoleIssuer(@NonNull CommandSender base) {
        this.base = base;
    }

    @NonNull
    @Override
    public CommandSender getBase() {
        return base;
    }
}
