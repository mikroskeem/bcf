package eu.mikroskeem.bcf.api.issuer;

import eu.mikroskeem.bcf.api.CommandRegistry;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public interface IssuerContext<I extends Issuer> {
    @NonNull
    CommandRegistry<?, ?, ?> getCommandRegistry();

    @NonNull
    I getIssuer();
}
