plugins {
    kotlin("jvm") version "1.7.21"
}

version = project.property("core.version")!!

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
}
