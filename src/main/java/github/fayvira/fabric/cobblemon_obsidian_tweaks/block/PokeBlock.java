//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.fayvira.fabric.cobblemon_obsidian_tweaks.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PokeBlock extends HorizontalFacingBlock implements Waterloggable {
  public static final MapCodec<PokeBlock> CODEC = createCodec(PokeBlock::new);
  public static final int MAX_AMOUNT = 4;
  public static final IntProperty AMOUNT = IntProperty.of("amount", 1, MAX_AMOUNT);
  public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
  private static final VoxelShape ONE_POKE_SHAPE = Block.createCuboidShape(5.0F, 0.0F, 5.0F, 11.0F, 1.0F, 11.0F);
  private static final VoxelShape TWO_POKE_SHAPE = Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 1.0F, 12.0F);
  private static final VoxelShape THREE_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 1.0F, 14.0F);
  private static final VoxelShape FOUR_POKE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 2.0F, 14.0F);

  @Override
  protected MapCodec<PokeBlock> getCodec() {
    return CODEC;
  }

  public PokeBlock(AbstractBlock.Settings settings) {
    super(settings);
    this.setDefaultState(
      this.getDefaultState().with(AMOUNT, 1).with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, Boolean.FALSE)
    );
  }

  public boolean canReplace(BlockState state, ItemPlacementContext context) {
    return !context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(AMOUNT) < 4 || state.isReplaceable() && (context.getStack().isEmpty() || !context.getStack().isOf(this.asItem()));
  }

  public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
    BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
    if (blockState.isOf(this)) {
      return blockState.cycle(AMOUNT);
    } else {
      BlockState blockstate = super.getPlacementState(ctx);
      return blockstate == null ? null : blockstate.with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }
  }

  public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
    if (state.get(WATERLOGGED)) {
      world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
    }

    return state;
  }

  public FluidState getFluidState(BlockState state) {
    return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : Fluids.EMPTY.getDefaultState();
  }

  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return switch (state.get(AMOUNT)) {
      case 4 -> FOUR_POKE_SHAPE;
      case 3 -> THREE_POKE_SHAPE;
      case 2 -> TWO_POKE_SHAPE;
      default -> ONE_POKE_SHAPE;
    };
  }

  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(AMOUNT, FACING, WATERLOGGED);
  }

  public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return sideCoversSmallSquare(world, pos.down(), Direction.UP);
  }
}
