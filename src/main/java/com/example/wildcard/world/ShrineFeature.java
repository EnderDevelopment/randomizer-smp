package com.example.wildcard.world;

import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class ShrineFeature {
    private ShrineFeature() { }
    public static void place(ServerWorld world, BlockPos origin) {
        BlockPos base = origin.down();
        if (!world.getBlockState(base).isSolidBlock(world, base)) return;
        for (int x = -2; x <= 2; x++) for (int z = -2; z <= 2; z++) world.setBlockState(base.add(x, 0, z), Blocks.POLISHED_DEEPSLATE.getDefaultState());
        for (int x = -1; x <= 1; x++) for (int z = -1; z <= 1; z++) if (Math.abs(x) + Math.abs(z) <= 1) world.setBlockState(base.add(x, 1, z), Blocks.AMETHYST_BLOCK.getDefaultState());
        world.setBlockState(base.up(1), Blocks.ENCHANTING_TABLE.getDefaultState());
        world.setBlockState(base.add(0, 2, 0), Blocks.SOUL_LANTERN.getDefaultState());
    }
}
