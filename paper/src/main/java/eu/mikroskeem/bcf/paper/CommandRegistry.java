package eu.mikroskeem.bcf.paper;

import eu.mikroskeem.bcf.api.completion.CompletionContext;
import eu.mikroskeem.bcf.api.issuer.IssuerContext;
import eu.mikroskeem.bcf.api.Permissions;
import eu.mikroskeem.bcf.core.ImplInjector;
import eu.mikroskeem.bcf.paper.issuers.PaperIssuer;
import eu.mikroskeem.bcf.paper.issuers.PlayerIssuer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.UUID;

/**
 * @author Mark Vainomaa
 */
public final class CommandRegistry extends eu.mikroskeem.bcf.api.CommandRegistry<PaperIssuer<?>, IssuerContext<PaperIssuer<?>>, CompletionContext<IssuerContext<PaperIssuer<?>>>> {
    {
        ImplInjector.setImpl(this);

        // Player
        registerConverter(Player.class, (arg, context) -> {
            Player foundPlayer = null;
            if (arg.length() == 36 || (arg.length() == 32 && arg.indexOf('-') == -1)) {
                try {
                    UUID uuid = UUID.fromString(arg);
                    foundPlayer = Bukkit.getPlayer(uuid);
                }
                catch (IllegalArgumentException ignored) {}
            }

            if (foundPlayer == null) {
                foundPlayer = Bukkit.getPlayerExact(arg);
            }

            // Additional checks
            if (foundPlayer != null) {
                if (context.getIssuer() instanceof PlayerIssuer && !context.getIssuer().hasPermission(Permissions.BCF_CAN_SEE_VANISHED)) {
                    // De-facto standard for checking whether player is vanished
                    List<MetadataValue> metadata = foundPlayer.getMetadata("vanished");
                    if (!metadata.isEmpty()) {
                        for (MetadataValue meta : metadata) {
                            if (meta.asBoolean()) {
                                // Player is vanished, set found player to null
                                foundPlayer = null;
                                break;
                            }
                        }
                    }
                }

                // TODO: more checks?
            }

            return foundPlayer;
        });

        registerCompletion(Player.class, context -> {
            return null; // TODO!
        });

        // Material
        registerConverter(Material.class, (arg, context) -> {
            return Material.matchMaterial(arg);
        });

        registerCompletion(Material.class, context -> {
            return null; // TODO!
        });
    }
}
