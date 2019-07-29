val checkerQualVersion: String by rootProject.extra
val kyoriTextVersion: String by rootProject.extra

sourceSets {
    create("ap") {
        compileClasspath = sourceSets["main"].run { compileClasspath + output }
    }
}

dependencies {
    api("net.kyori:text-api:$kyoriTextVersion")
    compileOnly("org.checkerframework:checker-qual:$checkerQualVersion")
}

val jar by tasks.getting(Jar::class) {
    from(sourceSets["ap"].output)
}