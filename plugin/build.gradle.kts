plugins {
    kotlin("jvm") version "1.7.10"
}

version = project.property("plugin.version")!!

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
    implementation(project(":core"))
    implementation(project(":image"))
    implementation(project(":video"))
}
