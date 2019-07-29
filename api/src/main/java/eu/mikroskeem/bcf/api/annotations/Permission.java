package eu.mikroskeem.bcf.api.annotations;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mark Vainomaa
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Permission {
    /**
     * Returns the permission node required to invoke this command or subcommand
     *
     * @return The permission node required to invoke this command or subcommand
     */
    @NonNull
    String value();
}
