//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.fayvira.fabric.faes_poke;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import github.fayvira.fabric.faes_poke.block.PokeBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FaesPoke implements ModInitializer {
  public static final String MOD_ID = "faes_poke";
  public static final String MOD_NAME = "FaesPoke";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    PokeBlocks.registerBlocks();
  }

  public static Identifier of(String path) {
    return Identifier.of(MOD_ID, path);
  }
}
