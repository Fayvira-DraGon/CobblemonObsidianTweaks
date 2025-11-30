val deleteFiles by tasks.registering(Delete::class) {
  delete(file(".idea/runConfigurations"))
}

tasks.named("ideaSyncTask") {
  dependsOn(deleteFiles)
}

plugins {
  id("java")
  id("dev.architectury.loom") version ("1.10-SNAPSHOT")
  id("architectury-plugin") version ("3.4-SNAPSHOT")
  kotlin("jvm") version "2.2.10"
}

group = "${project.property("mod_group")}"
version = "${project.property("mod_version")}"

/*
tasks.withType(JavaCompile).configureEach {
it.options.release = 21
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).all {
kotlinOptions {
jvmTarget = 21
}
}
*/

java {
  withSourcesJar()

  sourceCompatibility = JavaVersion.toVersion((project.property("java_version") as String).toInt())
  targetCompatibility = JavaVersion.toVersion((project.property("java_version") as String).toInt())
}

// fabricApi {
//   configureDataGeneration {
//     client = true
//   }
// }

architectury {
  fabric()
  platformSetupLoomIde()
}

loom {
  silentMojangMappingsLicense()

  @Suppress("UnstableApiUsage")
  mixin {
    defaultRefmapName.set("mixins.${project.property("mod_id")}.refmap.json")
  }

  runs {
    named("client") {
      client()
      property("mixin.debug.export", "true")
      property("mixin.dumpTargetOnFailure", "true")
      property("devauth.enabled", "true") // devauth: enable
      property("devauth.account", "main") // account type: minecraft
      programArg("--width=${project.property("window_width")}")
      programArg("--height=${project.property("window_height")}")
      ideConfigGenerated(true)
    }
  }
}

repositories {
  mavenCentral()
  // maven("https://maven.bawnorton.com/releases")  { name = "MixinSquared" }
  maven("https://maven.impactdev.net/repository/development/") { name = "Cobblemon" }
  maven("https://oss.sonatype.org/content/repositories/snapshots") { name = "Cobblemon" }
  maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") { name = "DevAuth" }
}

dependencies {
  implementation(kotlin("stdlib-${project.property("kotlin_stdlib")}"))
  // include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:" + propertiesMap["mixinsquared_version"]) ?: return@dependencies) ?: return@dependencies) // MixinSquared
  mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
  minecraft("net.minecraft:minecraft:${project.property("minecraft_version")}")
  modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("fabric_language_kotlin_version")}")
  modImplementation("net.fabricmc:fabric-loader:${project.property("fabric_loader_version")}")
  modRuntimeOnly("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
  modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${project.property("devauth_version")}")

  modImplementation("com.cobblemon:fabric:${project.property("cobblemon_version")}")
  // modImplementation("curse.maven:bridging-mod-533942:6269727") // Bridging Mod
}

tasks.processResources {
  inputs.property("version", project.property("mod_version"))
  filesMatching("fabric.mod.json") { expand(project.properties) }
  filesMatching("README.md") { expand(project.properties) }
  // filesMatching("${project.property("mod_id")}.mixins.json") { expand(project.properties) }
}

tasks.withType<AbstractArchiveTask> {
  archiveBaseName = project.property("mod_archives_name") as String
  archiveVersion = project.property("mod_version") as String
}
