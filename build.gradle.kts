allprojects {
    group = "eu.mikroskeem"
    version = "0.0.1-SNAPSHOT"
}

extra["checkerQualVersion"] = "2.9.0"
extra["kyoriTextVersion"] = "3.0.2"
extra["textAdapterBukkitVersion"] = "3.0.3"
extra["commodoreVersion"] = "1.3"

extra["paperApiVersion"] = "1.14.4-R0.1-SNAPSHOT"
extra["mikrocordApiVersion"] = "1.14-SNAPSHOT"

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()

        maven("https://maven.cu-mc.com/repository/maven-public")
        maven("https://papermc.io/repo/repository/maven-public")
        maven("https://libraries.minecraft.net/")
    }
}

subprojects {
    apply(plugin = "java-library")
}