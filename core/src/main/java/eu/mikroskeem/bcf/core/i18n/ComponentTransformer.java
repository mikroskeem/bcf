package eu.mikroskeem.bcf.core.i18n;

import net.kyori.text.Component;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;

/**
 * @author Mark Vainomaa
 */
public final class ComponentTransformer {
    public static <T extends Component, R extends TextComponent> R transform(T component) {
        if (component instanceof TranslatableComponent) {

        }

        return null;
    }

    private static TextComponent transformSingle(TranslatableComponent component) {
        return null;
    }
}
