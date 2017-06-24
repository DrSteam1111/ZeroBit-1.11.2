package com.drsteam1111.zerobit.worldgen;

import com.drsteam1111.zerobit.blocks.BlockIngotOres;
import com.drsteam1111.zerobit.config.ZbConfig;
import com.drsteam1111.zerobit.handlers.EnumHandler;
import com.drsteam1111.zerobit.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by edvin on 2017-06-02.
 */
public class OreGen implements IWorldGenerator {

    //World Generators
    private WorldGenerator copper_overworld;
    private WorldGenerator silver_overworld;
    private WorldGenerator tin_overworld;

    public OreGen() {
        copper_overworld = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockIngotOres.TYPE, EnumHandler.oreTypes.COPPER), 6);
        silver_overworld = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockIngotOres.TYPE, EnumHandler.oreTypes.SILVER), 3);
        tin_overworld = new WorldGenMinable(ModBlocks.ore.getDefaultState().withProperty(BlockIngotOres.TYPE, EnumHandler.oreTypes.TIN), 6);
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0: //Overworld
                this.runGenerator(copper_overworld, world, random, chunkX, chunkZ, 20 * ZbConfig.oreSpawnRateMultiplier, 0, 64);
                this.runGenerator(silver_overworld, world, random, chunkX, chunkZ, 15 * ZbConfig.oreSpawnRateMultiplier, 11, 54);
                this.runGenerator(tin_overworld, world, random, chunkX, chunkZ, 20 * ZbConfig.oreSpawnRateMultiplier, 0, 64);
                break;
            case 6: //Mining World
                this.runGenerator(copper_overworld, world, random, chunkX, chunkZ, 30 * ZbConfig.oreSpawnRateMultiplier, 0, 256);
                this.runGenerator(silver_overworld, world, random, chunkX, chunkZ, 30 * ZbConfig.oreSpawnRateMultiplier, 0, 256);
                this.runGenerator(tin_overworld, world, random, chunkX, chunkZ, 30 * ZbConfig.oreSpawnRateMultiplier, 0, 256);
                break;
        }
    }
}
