package eu.mikroskeem.bcf.mikrocord.issuers;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public final class PlayerIssuer implements MikroCordIssuer<ProxiedPlayer> {
    private final ProxiedPlayer base;

    public PlayerIssuer(@NonNull ProxiedPlayer base) {
        this.base = base;
    }

    @NonNull
    @Override
    public ProxiedPlayer getBase() {
        return base;
    }
}
