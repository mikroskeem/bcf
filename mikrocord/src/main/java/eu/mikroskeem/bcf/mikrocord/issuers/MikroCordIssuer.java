package eu.mikroskeem.bcf.mikrocord.issuers;

import eu.mikroskeem.bcf.api.issuer.Issuer;
import net.kyori.text.Component;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public interface MikroCordIssuer<T extends CommandSender> extends Issuer<T> {
    @NonNull
    @Override
    default String getName() {
        return getBase().getName();
    }

    @Override
    default boolean isPlayer() {
        return getBase() instanceof ProxiedPlayer;
    }

    @Override
    default boolean hasPermission(@NonNull String node) {
        return getBase().hasPermission(node);
    }

    @Override
    default void sendMessage(@NonNull Component component) {
        getBase().sendMessage(component);
    }
}
