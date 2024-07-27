import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "2.+"
    id("io.papermc.paperweight.userdev") version "1.7.+"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id("com.github.gmazzo.buildconfig") version "5.4.0"
}

val serverVersion: String by project

val packageString = "${properties["group"] as String}.${rootProject.name.lowercase()}"

val kSpigotVersion: String by project

repositories {
    mavenCentral()
    maven {
        name = "laturia"
        url = uri("https://repo.laturia.net/releases")
        credentials(PasswordCredentials::class)
        authentication {
            create<BasicAuthentication>("basic")
        }
    }
}

dependencies {
    paperweight.paperDevBundle("$serverVersion-R0.1-SNAPSHOT")

    // Minecraft
    library("net.axay:kspigot:$kSpigotVersion")

    // Kotlin
    implementation(kotlin("stdlib"))
    library("org.jetbrains.kotlinx:kotlinx-serialization-json:1.+")
    library("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.+")
}

buildConfig {
    className("BuildConfig")
    packageName(packageString)

    buildConfigField("KSPIGOT_VERSION", kSpigotVersion)
}

paper {
    group = properties["group"] as String
    main = "$packageString.Main"

    loader = "$packageString.loader.CustomLoader"

    name = properties["name"] as String
    author = properties["author"] as String
    description = properties["description"] as String
    website = properties["website"] as String

    generateLibrariesJson = true

    version = properties["version"] as String
    apiVersion = "1.21"

    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    serverDependencies {}
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