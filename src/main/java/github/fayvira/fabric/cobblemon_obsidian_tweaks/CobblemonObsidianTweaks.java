//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.fayvira.fabric.cobblemon_obsidian_tweaks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import github.fayvira.fabric.cobblemon_obsidian_tweaks.block.PokeBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CobblemonObsidianTweaks implements ModInitializer {
  public static final String MOD_ID = "cobblemon_obsidian_tweaks";
  public static final String MOD_NAME = "Cobblemon Obsidian Tweaks";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    LOGGER.info("Initializing Mod: {}.", MOD_NAME);

    PokeBlocks.registerBlocks();
  }

  public static Identifier of(String path) {
    return Identifier.of(MOD_ID, path);
  }
}
