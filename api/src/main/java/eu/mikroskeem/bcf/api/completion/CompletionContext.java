package eu.mikroskeem.bcf.api.completion;

import eu.mikroskeem.bcf.api.issuer.IssuerContext;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public interface CompletionContext<IC extends IssuerContext<?>> {
    @NonNull
    IC getIssuerContext();
}