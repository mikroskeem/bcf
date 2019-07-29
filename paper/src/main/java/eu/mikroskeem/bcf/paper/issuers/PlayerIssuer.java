package eu.mikroskeem.bcf.paper.issuers;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public class PlayerIssuer implements PaperIssuer<Player> {
    private final Player base;

    public PlayerIssuer(Player base) {
        this.base = base;
    }

    @NonNull
    @Override
    public Player getBase() {
        return base;
    }
}
