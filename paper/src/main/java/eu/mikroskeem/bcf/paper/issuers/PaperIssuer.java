package eu.mikroskeem.bcf.paper.issuers;

import eu.mikroskeem.bcf.api.issuer.Issuer;
import net.kyori.text.Component;
import net.kyori.text.adapter.bukkit.TextAdapter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public interface PaperIssuer<T extends CommandSender> extends Issuer<T> {
    @Override
    @NonNull
    default String getName() {
        return getBase().getName();
    }

    @Override
    default boolean isPlayer() {
        return getBase() instanceof Player;
    }

    @Override
    default boolean hasPermission(@NonNull String node) {
        return getBase().hasPermission(node);
    }

    @Override
    default void sendMessage(@NonNull Component component) {
        TextAdapter.sendComponent(getBase(), component);
    }
}
