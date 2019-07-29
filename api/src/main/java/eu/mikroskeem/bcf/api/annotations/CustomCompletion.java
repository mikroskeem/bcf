package eu.mikroskeem.bcf.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mark Vainomaa
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CustomCompletion {
    /**
     * Custom completion identifier
     *
     * @return
     */
    String id();
}
