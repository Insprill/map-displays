plugins {
    kotlin("jvm") version "1.7.10"
}

version = project.property("image.version")!!

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
    implementation(project(":core"))

    // Test dependencies
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.19:2.119.3")
}

tasks.test {
    useJUnitPlatform()
}
