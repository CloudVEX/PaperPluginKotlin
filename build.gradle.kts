import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev") version "1.7.+"
    id("xyz.jpenilla.run-paper") version "2.2.2" // newer version does not work currently
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

val serverVersion: String by project

val packageString = "${properties["group"] as String}.${rootProject.name.lowercase()}"

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("$serverVersion-R0.1-SNAPSHOT")

    // Minecraft
    library("net.axay:kspigot:1.20.4")

    // Kotlin
    implementation(kotlin("stdlib"))
    library("org.jetbrains.kotlinx:kotlinx-serialization-json:1.+")
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.+")
}

bukkit {

    name = properties["name"] as String
    author = properties["author"] as String
    description = properties["description"] as String
    website = properties["website"] as String

    group = properties["group"] as String
    main = "$packageString.Main"

    version = properties["version"] as String
    apiVersion = "1.20"

    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    depend = listOf()
    softDepend = listOf()
}

kotlin {
    jvmToolchain(21)
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
    runServer {
        minecraftVersion(serverVersion)
    }
}