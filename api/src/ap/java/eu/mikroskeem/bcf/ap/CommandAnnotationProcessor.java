package eu.mikroskeem.bcf.ap;

import eu.mikroskeem.bcf.api.annotations.ArgumentName;
import eu.mikroskeem.bcf.api.annotations.Command;
import eu.mikroskeem.bcf.api.annotations.CustomCompletion;
import eu.mikroskeem.bcf.api.annotations.Default;
import eu.mikroskeem.bcf.api.annotations.Help;
import eu.mikroskeem.bcf.api.annotations.Permission;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * @author Mark Vainomaa
 */
public class CommandAnnotationProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "BCF AP initialized");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(
                ArgumentName.class.getName(),
                Command.class.getName(),
                CustomCompletion.class.getName(),
                Default.class.getName(),
                Help.class.getName(),
                Permission.class.getName()
        );
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(Command.class)) {
            if (element.getKind() != ElementKind.CLASS && element.getKind() != ElementKind.METHOD) {
                processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.ERROR, "@Command can be only used with methods or classes!", element);
            }

            // Check if command name or aliases are empty
            Command c = element.getAnnotation(Command.class);
            if (c.value().isEmpty() || c.value().indexOf(' ') != -1) {
                processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.ERROR, "Command name must not be empty or contain spaces!", element);
            }
            for (String alias : c.aliases()) {
                if (alias.isEmpty() || alias.indexOf(' ') != -1) {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Command alias must not be empty or contain spaces!", element);
                }
            }

            // Check if method's class has @Command annotation
            if (element.getKind() == ElementKind.METHOD) {
                if (element.getEnclosingElement().getKind() != ElementKind.CLASS) {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Command handler is in abnormal place, must be inside class", element);
                } else if (element.getEnclosingElement().getAnnotation(Command.class) == null) {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "Command handler method must be defined inside class with @Command annotation", element);
                }
            }

            // Check if command has multiple subcommands
            if (element.getKind() == ElementKind.CLASS) {
                int subcommands = 0;
                boolean hasDefault = false;

                for (Element enclosed : element.getEnclosedElements()) {
                    if (enclosed.getKind() != ElementKind.METHOD)
                        continue;

                    Command command = enclosed.getAnnotation(Command.class);
                    if (command != null) {
                        // All command handlers must be public for performance reasons
                        if (!enclosed.getModifiers().contains(Modifier.PUBLIC)) {
                            processingEnv.getMessager()
                                    .printMessage(Diagnostic.Kind.ERROR, "All @Command annotated methods must be public!", enclosed);
                        }

                        ++subcommands;
                    }

                    if (enclosed.getAnnotation(Default.class) != null) {
                        if (!hasDefault) {
                            hasDefault = true;
                        } else {
                            // Prevent having Default annotation twice
                            processingEnv.getMessager()
                                    .printMessage(Diagnostic.Kind.ERROR, "Only one method can have @Default", enclosed);
                        }
                    }
                }

                // Check for abnormal command classes
                if (subcommands > 0 && !hasDefault) {
                    // Atleast one subcommand and no default defined - if wrong subcommand is used then
                    // BCF does not really know how to handle this.
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "At least one subcommand is defined but no @Default handler is marked!", element);
                } else if (subcommands == 0 && !hasDefault) {
                    processingEnv.getMessager()
                            .printMessage(Diagnostic.Kind.ERROR, "No subcommands nor default handler marked", element);
                }

                processingEnv.getMessager()
                        .printMessage(Diagnostic.Kind.NOTE, String.format("methods=%d, hasDefault=%s", subcommands, hasDefault));
            }
        }

        return false;
    }
}
