plugins {
    kotlin("jvm") version "1.7.10"
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

