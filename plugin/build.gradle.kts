import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.github.johnrengelman.shadow") version ("7.1.2")
    id("com.rikonardo.papermake") version "1.0.4"
}

version = project.property("plugin.version")!!

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
    implementation(project(":core"))
    implementation(project(":image"))
    implementation(project(":video"))
}

tasks.processResources {
    filesMatching("*.yml") {
        expand("version" to version)
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
    minimize()
}

tasks.withType<ConfigureShadowRelocation> {
    target = tasks.shadowJar.get()
}

tasks.build {
    dependsOn(tasks.shadowJar)
    dependsOn(tasks.withType<ConfigureShadowRelocation>())
}
