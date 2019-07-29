package eu.mikroskeem.bcf.mikrocord.issuers;

import net.md_5.bungee.api.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public final class ConsoleIssuer implements MikroCordIssuer<CommandSender> {
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
