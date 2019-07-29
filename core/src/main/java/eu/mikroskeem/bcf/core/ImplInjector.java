package eu.mikroskeem.bcf.core;

import eu.mikroskeem.bcf.api.CommandRegistry;

import java.lang.reflect.Field;

/**
 * @author Mark Vainomaa
 */
public final class ImplInjector {
    private static final Field registryInstanceField;

    public static void setImpl(CommandRegistry registry) {
        try {
            registryInstanceField.set(null, registry);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static {
        Field field;
        try {
            field = CommandRegistry.class.getDeclaredField("instance");
            field.setAccessible(true);
        } catch (Exception e) {
            throw new Error("Failed to get CommadRegistry 'instance' field", e);
        }
        registryInstanceField = field;
    }
}
