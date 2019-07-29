package eu.mikroskeem.bcf.api.issuer;

import net.kyori.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;

/**
 * The command issuer
 *
 * @author Mark Vainomaa
 */
public interface Issuer<T> {
    /**
     * Gets the issuer name
     *
     * @return Issuer name
     */
    @NonNull
    String getName();

    /**
     * Returns whether given issuer is in-game player or not
     *
     * @return Whether given issuer is in-game player or not
     */
    boolean isPlayer();

    /**
     * Returns whether given issuer has a permission
     *
     * @param node Permission node to check
     * @return Whether issuer has a permission or not
     */
    boolean hasPermission(@NonNull String node);

    /**
     * Sends a message to the issuer
     *
     * @param component Component to send
     */
    void sendMessage(@NonNull Component component);

    /**
     * Sends a collection of messages to the issuer
     *
     * @param components Collection of components
     */
    default void sendMessage(@NonNull Collection<Component> components) {
        for (Component component : components) {
            sendMessage(component);
        }
    }

    /**
     * Gets the wrapped command issuer (platform specific)
     *
     * @return Wrapped command issuer
     */
    @NonNull
    T getBase();
}
