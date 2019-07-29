val paperApiVersion: String by rootProject.extra
val textAdapterBukkitVersion: String by rootProject.extra
val commodoreVersion: String by rootProject.extra

dependencies {
    implementation(project(":core"))
    implementation("me.lucko:commodore:$commodoreVersion")
    implementation("net.kyori:text-adapter-bukkit:$textAdapterBukkitVersion")
    compileOnly("com.destroystokyo.paper:paper-api:$paperApiVersion")
}