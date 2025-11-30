package github.fayvira.fabric.cobblemon_obsidian_tweaks.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PokeFiverBlock extends PokeBlock {
  private static final VoxelShape ONE_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 4.0F, 14.0F);
  private static final VoxelShape TWO_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 8.0F, 14.0F);
  private static final VoxelShape THREE_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 11.0F, 14.0F);
  private static final VoxelShape FOUR_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 14.0F, 14.0F);

  public PokeFiverBlock(AbstractBlock.Settings settings) {
    super(settings);
  }

  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return switch (state.get(AMOUNT)) {
      case 4 -> FOUR_POKE_SHAPE;
      case 3 -> THREE_POKE_SHAPE;
      case 2 -> TWO_POKE_SHAPE;
      default -> ONE_POKE_SHAPE;
    };
  }
}
