package eu.mikroskeem.bcf.api.annotations;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The command annotation.
 *
 * @author Mark Vainomaa
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Command {
    /**
     * Returns the command name
     *
     * @return Command name
     */
    @NonNull
    String value();

    /**
     * Returns the command aliases
     *
     * @return Command aliases
     */
    @NonNull
    String[] aliases() default {};
}
