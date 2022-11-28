import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    kotlin("jvm") version "1.7.22"
    id("com.github.johnrengelman.shadow") version ("7.1.2")
    id("com.rikonardo.papermake") version "1.0.4"
}

version = project.property("plugin.version")!!

repositories {
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${project.property("common.spigot-api.version")!!}")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation(project(":core"))
    implementation(project(":image"))
    implementation(project(":video"))
}

tasks.processResources {
    filesMatching("*.yml") {
        expand("version" to version)
    }
}

tasks.jar {
    enabled = false
}

task<ConfigureShadowRelocation>("relocateShadowJar") {
    target = tasks.shadowJar.get()
    prefix = "net.insprill.mapdisplays.plugin.libs"
}

tasks.shadowJar {
    dependsOn(tasks.getByName("relocateShadowJar"))
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
    minimize()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}
