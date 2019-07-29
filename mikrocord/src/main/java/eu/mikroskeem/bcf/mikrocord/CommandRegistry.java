package eu.mikroskeem.bcf.mikrocord;

import eu.mikroskeem.bcf.api.completion.CompletionContext;
import eu.mikroskeem.bcf.api.issuer.IssuerContext;
import eu.mikroskeem.bcf.core.ImplInjector;
import eu.mikroskeem.bcf.mikrocord.issuers.MikroCordIssuer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Mark Vainomaa
 */
public final class CommandRegistry extends eu.mikroskeem.bcf.api.CommandRegistry<MikroCordIssuer<?>, IssuerContext<MikroCordIssuer<?>>, CompletionContext<IssuerContext<MikroCordIssuer<?>>>> {
    {
        ImplInjector.setImpl(this);

        // ** Register common converters

        // ProxiedPlayer
        registerConverter(ProxiedPlayer.class, (arg, context) -> {
            if (arg.length() == 36 || (arg.length() == 32 && arg.indexOf('-') == -1)) {
                try {
                    UUID uuid = UUID.fromString(arg);
                    return ProxyServer.getInstance().getPlayer(uuid);
                }
                catch (IllegalArgumentException ignored) {}
            }

            // TODO: player visibility checks?

            return ProxyServer.getInstance().getPlayer(arg);
        });

        registerCompletion(ProxiedPlayer.class, context -> {
            // TODO: player visibility checks?
            return ProxyServer.getInstance().getPlayers().stream()
                    .map(CommandSender::getName)
                    .collect(Collectors.toList());
        });

        // ServerInfo
        registerConverter(ServerInfo.class, (arg, issuer) -> {
            ServerInfo info = ProxyServer.getInstance().getServerInfo(arg);
            // TODO: server visibility checks?
            return info;
        });

        registerCompletion(ServerInfo.class, context -> {
            // TODO: server visibility checks?
            return new ArrayList<>(ProxyServer.getInstance().getServersCopy().keySet());
        });
    }
}
