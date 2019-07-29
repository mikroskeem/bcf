package eu.mikroskeem.bcf.api;

import eu.mikroskeem.bcf.api.completion.CompletionContext;
import eu.mikroskeem.bcf.api.issuer.Issuer;
import eu.mikroskeem.bcf.api.issuer.IssuerContext;
import net.kyori.text.TranslatableComponent;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.awt.TextComponent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Mark Vainomaa
 */
public abstract class CommandRegistry<
        I extends Issuer,
        IC extends IssuerContext<I>,
        CC extends CompletionContext<IC>> {
    @MonotonicNonNull
    private static final CommandRegistry<?, ?, ?> instance = null;

    private final Map<Class<?>, BiFunction<String, IC, ?>> argConverters = new HashMap<>();
    private final Map<Class<?>, BiFunction<TranslatableComponent, I, TextComponent>> translationProviders = new HashMap<>();
    private final Map<Class<?>, Function<IC, List<String>>> completers = new HashMap<>();
    //private final Map<String, >

    public void registerCommand(Class<?> clazz) {

    }

    /**
     * Registers a command parameter converter
     *
     * @param clazz Target type class
     * @param converter String to type converter
     * @param <T> Target type
     */
    public <T> void registerConverter(@NonNull Class<T> clazz, @NonNull BiFunction<@NonNull String, @NonNull IC, @Nullable T> converter) {
        argConverters.put(clazz, converter);
    }

    /**
     * Registers a completion provider
     *
     * @param clazz Target type class
     * @param completer Type to string converter
     * @param <T> Completion type
     */
    public <T> void registerCompletion(@NonNull Class<T> clazz, @NonNull Function<@NonNull IC, @Nullable List<String>> completer) {
        completers.put(clazz, completer);
    }

    /**
     * Gets currently set command registry instance
     *
     * @return Command registry
     */
    @NonNull
    public static CommandRegistry<?, ?, ?> getInstance() {
        return instance;
    }
}
