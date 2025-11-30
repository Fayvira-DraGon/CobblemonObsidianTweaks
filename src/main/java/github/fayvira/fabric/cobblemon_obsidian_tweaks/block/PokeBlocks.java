package github.fayvira.fabric.cobblemon_obsidian_tweaks.block;

import github.fayvira.fabric.cobblemon_obsidian_tweaks.CobblemonObsidianTweaks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;

import static github.fayvira.fabric.cobblemon_obsidian_tweaks.CobblemonObsidianTweaks.LOGGER;
import static github.fayvira.fabric.cobblemon_obsidian_tweaks.CobblemonObsidianTweaks.MOD_NAME;

public class PokeBlocks {
  public static final Block POKE = registerBlock("poke", new PokeBlock(AbstractBlock.Settings.create().nonOpaque().strength(0.2F).mapColor(MapColor.GOLD).sounds(BlockSoundGroup.METAL).pistonBehavior(PistonBehavior.DESTROY)));
  public static final Block PokeFiverBlock = registerBlock("poke_fiver", new PokeFiverBlock(AbstractBlock.Settings.copy(POKE)));

  @SuppressWarnings("unused")
  public static final ItemGroup COBBLEMON_OBSIDIAN_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
    CobblemonObsidianTweaks.of("poke"),
    new ItemGroup.Builder(null, -1).displayName(Text.translatable("itemgroup.cobblemon_obsidian_tweaks")).icon(() -> new ItemStack(POKE)).entries((displayContext, entries) -> {
      entries.add(POKE);
      entries.add(PokeFiverBlock);
    }).build());

  private static Block registerBlock(String name, Block block) {
    registerBlockItem(name, block);
    return Registry.register(Registries.BLOCK, CobblemonObsidianTweaks.of(name), block);
  }

  private static void registerBlockItem(String name, Block block) {
    Registry.register(Registries.ITEM, CobblemonObsidianTweaks.of(name), new BlockItem(block, new Item.Settings()));
  }

  public static void registerBlocks() {
    LOGGER.info("Registering Blocks for Mod: {}.", MOD_NAME);
    LOGGER.info("Registering Item Groups for " + MOD_NAME);
  }
}
