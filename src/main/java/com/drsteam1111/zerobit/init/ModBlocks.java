package com.drsteam1111.zerobit.init;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.ZeroBit;
import com.drsteam1111.zerobit.blocks.*;
import com.drsteam1111.zerobit.blocks.item.*;
import com.drsteam1111.zerobit.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.drsteam1111.zerobit.handlers.EnumHandler.*;

/**
 * Created by edvin on 2017-06-01.
 */
public class ModBlocks {

        public static Block machineFrame;
        public static Block ore;
        public static Block block;
        public static Block compressor;
        public static Block breaker;
        public static Block levitator;

        public static void init() {
            machineFrame = new BlockMachineFrame("machine_frame");
            ore = new BlockIngotOres("ore");
            block = new BlockIngotBlocks("block");
            compressor = new BlockCompressor("compressor");
            breaker = new BlockBreaker("block_breaker");
            levitator = new BlockLevitator("levitator");
        }

        public static void register() {
            registerBlock(machineFrame, new ItemBlockMeta(machineFrame));
            registerBlock(ore, new ItemIngotOres(ore));
            registerBlock(block, new ItemIngotBlocks(block));
            registerBlock(compressor);
            registerBlock(breaker, new ItemBlockBreaker(breaker));
            registerBlock(levitator, new ItemBlockLevitator(levitator));
        }

        public static void registerRenders() {
            registerRender(compressor);
            registerRender(levitator);

            for(int i = 0; i < MachineTypes.values().length; i++) {
                registerRender(breaker, i, "block_breaker_" + MachineTypes.values()[i].getName());
                registerRender(machineFrame, i, "machine_frame_" + MachineTypes.values()[i].getName());
            }
            for (int i = 0; i < IngotTypes.values().length; i++) {
                registerRender(block, i, "block_" + IngotTypes.values()[i].getName());
            }
            for (int i = 0; i < oreTypes.values().length; i++) {
                registerRender(ore, i, "ore_" + oreTypes.values()[i].getName());
            }
        }

        public static void registerBlock(Block block) {
            block.setCreativeTab(ZeroBit.zeroBitTab);
            GameRegistry.register(block);
            GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            Utils.getLogger().info("Registered Blocks: " + block.getUnlocalizedName().substring(5));
        }

        public static void registerBlock(Block block, ItemBlock itemBlock) {
            block.setCreativeTab(ZeroBit.zeroBitTab);
            GameRegistry.register(block);
            GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
            Utils.getLogger().info("Registered Blocks: " + block.getUnlocalizedName().substring(5));
        }

        public static void registerRender(Block block) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Ref.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
            Utils.getLogger().info("Register Block Render for: " + block.getUnlocalizedName().substring(5));
        }

        public static void registerRender(Block block, int meta, String fileName) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Ref.MODID, fileName), "inventory"));
            Utils.getLogger().info("Register Block Render for: " + block.getUnlocalizedName().substring(5));
        }
}