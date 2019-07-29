package eu.mikroskeem.bcf.api.exception;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Mark Vainomaa
 */
public final class NoPermission extends RuntimeException {
    private final String permissionNode;

    public NoPermission(@NonNull String permissionNode) {
        this.permissionNode = permissionNode;
    }

    @NonNull
    public String getPermissionNode() {
        return permissionNode;
    }
}
