val deleteRunConfigs by tasks.registering(Delete::class) {
  delete(file(".idea/runConfigurations"))
}

tasks.named("ideaSyncTask") {
  dependsOn(deleteRunConfigs)
}

plugins {
  id("java")
  id("dev.architectury.loom") version ("1.9-SNAPSHOT")
  id("architectury-plugin") version ("3.4-SNAPSHOT")
  kotlin("jvm") version "2.1.20"
}

val propertiesMap: Map<String, Any> = mapOf(
  "architectury_api_version" to "13.0.8",
  "cloth_config_version" to "15.0.140",
  "cobblemon_mega_showdown_version" to "1189523:6677424",
  "cobblemon_sources_version" to "1.6.1+1.21.1-sources",
  "cobblemon_version" to "1.6.1+1.21.1",
  "devauth_version" to "1.2.1",
  "fabric_api_version" to "0.116.3+1.21.1",
  "fabric_loader_version" to "0.16.14",
  "forge_config_api_port_version" to "21.1.3",
  "java_version" to 21,
  "kotlin_stdlib" to "jdk8",
  "kotlin_version" to "1.13.3+kotlin.2.1.21",
  "minecraft_version" to "1.21.1",
  "mixinsquared_version" to "0.2.0",
  "mod_archives_name" to "Cobblemon Obsidian Tweaks",
  "mod_authors" to "Fayvira-DraGon",
  "mod_contribution_0" to ": Artwork — coin assets",
  "mod_contribution_1" to ": Artwork — mod icon",
  "mod_contributor_0" to "Gregorius",
  "mod_contributor_1" to "Mechavoids",
  "mod_contributor_contact_0" to "https://linktr.ee/gregorius02",
  "mod_contributor_contact_1" to "https://linktr.ee/mechavoids",
  "mod_description" to "Various tweaks for the Cobblemon Obsidian server.",
  "mod_github" to "https://github.com/Fayvira-DraGon/CobblemonObsidianTweaks.git",
  "mod_group" to "github.fayvira.fabric",
  "mod_id" to "cobblemon_obsidian_tweaks",
  "mod_license" to "MIT License",
  "mod_modrinth" to "https://modrinth.com/mod/cobblemon-obsidian-tweaks",
  "mod_name" to "CobblemonObsidianTweaks",
  "mod_version" to "0.2.1-1.21.1",
  "modmenu_version" to "11.0.3",
  "radical_cobblemon_trainers_api_version" to "1152792:6734355",
  "radical_cobblemon_trainers_version" to "1009534:6692231",
  "supermartijn642s_config_lib_version" to "438332:5546988",
  "trinkets_version" to "3.10.0",
  "window_height" to (810 * 0.6).toInt(),
  "window_width" to (1440 * 0.6).toInt(),
  "yarn_mappings" to "1.21.1+build.3:v2"
)

java {
  withSourcesJar()
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
  @Suppress("UnstableApiUsage")
  mixin {
    defaultRefmapName.set("mixins." + propertiesMap["mod_id"] as String + ".refmap.json")
  }

  runs {
    named("client") {
      client()
      property("mixin.debug.export", "true")
      property("mixin.dumpTargetOnFailure", "true")
      property("devauth.enabled", "true") // devauth: enable
      // property("devauth.enabled", "false") // devauth: disable
      property("devauth.account", "main") // account type: minecraft
      programArg("--width=" + propertiesMap["window_width"])
      programArg("--height=" + propertiesMap["window_height"])
      ideConfigGenerated(true)
    }
  }
}

repositories {
  mavenCentral()
  maven("https://maven.architectury.dev/dev/architectury/architectury-fabric") { name = "Architectury" }
  maven("https://maven.shedaniel.me/") { name = "Cloth-Config" }
  maven("https://maven.impactdev.net/repository/development/") { name = "Cobblemon" }
  maven("https://cursemaven.com") { name = "CurseForge: Cobblemon Mega Showdown, Cobblemon Radical Trainers, SuperMartijn642's Config Lib"}
  maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1") { name = "DevAuth" }
  maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") { name = "Fuzs Mod Resources: Forge Config Api Port" }
  maven("https://maven.ladysnake.org/releases") { name = "Ladysnake Libs: Trinkets" }
  maven("https://maven.bawnorton.com/releases")  { name = "MixinSquared" }
  maven("https://maven.terraformersmc.com/") { name = "TerraformersMC: ModMenu, Trinkets" }
  // maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") { name = "GeckoLib"; content { includeGroup("software.bernie.geckolib") }; }
  // maven("https://oss.sonatype.org/content/repositories/snapshots") { name = "Cobblemon" }
}

dependencies {
  implementation(kotlin("stdlib-" + propertiesMap["kotlin_stdlib"]))
  include(implementation(annotationProcessor("com.github.bawnorton.mixinsquared:mixinsquared-fabric:" + propertiesMap["mixinsquared_version"]) ?: return@dependencies) ?: return@dependencies) // mixinsquared
  mappings("net.fabricmc:yarn:" + propertiesMap["yarn_mappings"]) // mappings
  minecraft("net.minecraft:minecraft:" + propertiesMap["minecraft_version"]) // minecraft
  modApi("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:" + propertiesMap["forge_config_api_port_version"]) // forge-config-api-port
  modApi("me.shedaniel.cloth:cloth-config-fabric:" + propertiesMap["cloth_config_version"]) { exclude("net.fabricmc.fabric-api") } // cloth config
  modImplementation("com.cobblemon:fabric:" + propertiesMap["cobblemon_sources_version"]) // cobblemon
  modImplementation("com.cobblemon:fabric:" + propertiesMap["cobblemon_version"]) // cobblemon
  modImplementation("com.terraformersmc:modmenu:" + propertiesMap["modmenu_version"]) // modmenu
  modImplementation("curse.maven:cobblemon-mega-showdown-" + propertiesMap["cobblemon_mega_showdown_version"]) // cobblemon-mega-showdown
  // modImplementation("curse.maven:radical-cobblemon-trainers-api-" + propertiesMap["radical_cobblemon_trainers_api_version"]) // radical-cobblemon-trainers-api
  // modImplementation("curse.maven:radical-cobblemon-trainers-mod-" + propertiesMap["radical_cobblemon_trainers_version"]) // radical-cobblemon-trainers
  modImplementation("curse.maven:supermartijn642s-config-lib-" + propertiesMap["supermartijn642s_config_lib_version"]) // supermartijn642s-config-lib
  modImplementation("dev.architectury:architectury-fabric:" + propertiesMap["architectury_api_version"])
  modImplementation("dev.emi:trinkets:" + propertiesMap["trinkets_version"]) // trinkets
  modImplementation("net.fabricmc.fabric-api:fabric-api:" + propertiesMap["fabric_api_version"]) // fabric api
  modImplementation("net.fabricmc:fabric-language-kotlin:" + propertiesMap["kotlin_version"]) // kotlin
  modImplementation("net.fabricmc:fabric-loader:" + propertiesMap["fabric_loader_version"]) // mod loader
  modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:" + propertiesMap["devauth_version"]) // devauth
}

tasks.processResources {
  inputs.property("version", propertiesMap["mod_version"])
  filesMatching("fabric.mod.json") { expand(propertiesMap) }
  filesMatching("README.md") { expand(propertiesMap) }
  filesMatching(propertiesMap["mod_id"] as String + ".mixins.json") { expand(propertiesMap) }
}

tasks.withType<AbstractArchiveTask> {
  archiveBaseName = propertiesMap["mod_archives_name"] as String
  archiveVersion = propertiesMap["mod_version"] as String
}
