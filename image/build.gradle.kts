plugins {
    kotlin("jvm") version "1.7.10"
}

version = project.property("image.version")!!

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
}
