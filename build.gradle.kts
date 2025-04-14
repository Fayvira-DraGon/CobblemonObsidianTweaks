plugins {
  id("java")
  id("dev.architectury.loom") version ("1.9-SNAPSHOT")
  id("architectury-plugin") version ("3.4-SNAPSHOT")
  kotlin("jvm") version "2.1.20"
}

val propertiesMap = mapOf(
  "cobblemon_version" to "1.6.1+1.21.1",
  "devauth_version" to "1.2.1",
  "fabric_api_version" to "0.115.4+1.21.1",
  "fabric_loader_version" to "0.16.10",
  "java_version" to 21,
  "kotlin_stdlib" to "jdk8",
  "kotlin_version" to "1.13.2+kotlin.2.1.20",
  "minecraft_version" to "1.21.1",
  "mod_archives_name" to "Fae's Poké",
  "mod_authors" to "Fayvira-DraGon",
  "mod_credits" to "Blue Azul, Laxan3000, & Smokiemario",
  "mod_description" to "Isolating the Cobblefoods Poke into a standalone mod!",
  "mod_github" to "https://github.com/Fayvira-DraGon/FaesPoke.git",
  "mod_group" to "github.fayvira.fabric",
  "mod_id" to "faes_poke",
  "mod_license" to "MIT License",
  "mod_modrinth" to "https://modrinth.com/mod/faes-poke",
  "mod_name" to "FaesPoke",
  "mod_version" to "1.4.0-1.20.1",
  "yarn_mappings" to "1.21.1+build.3:v2"
)

java {
  withSourcesJar()
}

architectury {
  fabric()
  platformSetupLoomIde()
}

loom {
  @Suppress("UnstableApiUsage")
  mixin {
    defaultRefmapName.set("mixins." + propertiesMap["mod_id"] as String + ".refmap.json")
  }

  runs {
    create("devauth") {
      client()
      property("devauth.enabled", "true") // devauth: enable
      property("devauth.account", "main") // account type: minecraft
    }
  }
}

repositories {
  mavenCentral()
  maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven")
  maven("https://maven.impactdev.net/repository/development/") { name = "Cobblemon" }
  maven("https://oss.sonatype.org/content/repositories/snapshots") { name = "Unknown" }
  maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") { name = "DevAuth" }
}

dependencies {
  implementation(kotlin("stdlib-" + propertiesMap["kotlin_stdlib"])) // kotlin-stdlib
  mappings("net.fabricmc:yarn:" + propertiesMap["yarn_mappings"]) // mappings
  minecraft("net.minecraft:minecraft:" + propertiesMap["minecraft_version"]) // minecraft
  modImplementation("com.cobblemon:fabric:" + propertiesMap["cobblemon_version"]) // cobblemon
  modImplementation("net.fabricmc.fabric-api:fabric-api:" + propertiesMap["fabric_api_version"]) // fabric api
  modImplementation("net.fabricmc:fabric-language-kotlin:" + propertiesMap["kotlin_version"]) // kotlin
  modImplementation("net.fabricmc:fabric-loader:" + propertiesMap["fabric_loader_version"]) // mod loader
  modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:" + propertiesMap["devauth_version"]) // devauth
}

tasks.processResources {
  filesMatching("fabric.mod.json") { expand(propertiesMap) }
  filesMatching(propertiesMap["mod_id"] as String + ".mixins.json") { expand(propertiesMap) }
}

tasks.withType<AbstractArchiveTask> {
  archiveBaseName = propertiesMap["mod_archives_name"] as String
  archiveVersion = propertiesMap["mod_version"] as String
}
