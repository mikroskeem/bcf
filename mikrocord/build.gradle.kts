val mikrocordApiVersion: String by rootProject.extra

dependencies {
    annotationProcessor(project(":api"))
    implementation(project(":core"))
    compileOnly("eu.mikroskeem.mikrocord:mikrocord-api:$mikrocordApiVersion")
}