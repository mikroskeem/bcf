package eu.mikroskeem.bcf.mikrocord.sample;

import eu.mikroskeem.bcf.api.annotations.Command;
import eu.mikroskeem.bcf.api.annotations.Default;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * @author Mark Vainomaa
 */
@Command("sample")
public final class SampleCommand {

    public void x(ProxiedPlayer player) {

    }

    @Default
    public void defaultHandler(ProxiedPlayer player) {

    }
}
