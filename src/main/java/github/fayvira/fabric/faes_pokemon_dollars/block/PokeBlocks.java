//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.fayvira.fabric.faes_pokemon_dollars.block;

import com.cobblemon.mod.common.item.group.CobblemonItemGroups;
import github.fayvira.fabric.faes_pokemon_dollars.FaesPokeDollars;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class PokeBlocks {
  @SuppressWarnings("deprecation")
  public static final Block POKE = registerBlock("poke", new PokeBlock(AbstractBlock.Settings.create().nonOpaque().notSolid().strength(0.2F).mapColor(MapColor.GOLD).sounds(BlockSoundGroup.METAL).pistonBehavior(PistonBehavior.DESTROY)));
  public static final Block POKE_PILE = registerBlock("poke_pile", new PokePileBlock(AbstractBlock.Settings.copy(POKE)));

  private static Block registerBlock(String name, Block block) {
    registerBlockItem(name, block);
    return Registry.register(Registries.BLOCK, FaesPokeDollars.of(
    name), block);
  }

  private static void registerBlockItem(String name, Block block) {
    Registry.register(Registries.ITEM, FaesPokeDollars.of(name), new BlockItem(block, new Item.Settings()));
  }

  public static void registerBlocks() {
    FaesPokeDollars.LOGGER.info("Registering Blocks for Mod: {}.", FaesPokeDollars.MOD_NAME);

    ItemGroupEvents.modifyEntriesEvent(CobblemonItemGroups.getBLOCKS_KEY()).register((content) -> {
      content.add(POKE);
      content.add(POKE_PILE);
    });
  }
}
