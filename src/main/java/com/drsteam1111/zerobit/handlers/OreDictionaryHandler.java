package com.drsteam1111.zerobit.handlers;

import com.drsteam1111.zerobit.init.ModBlocks;
import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by edvin on 2017-06-02.
 */
public class OreDictionaryHandler {

    public static void registerOreDictionary() {
        OreDictionary.registerOre("chipBasic", new ItemStack(ModItems.chip, 1, 0)); //Basic Chip
        OreDictionary.registerOre("chipAdvanced", new ItemStack(ModItems.chip, 1, 1)); //Advanced Chip
        OreDictionary.registerOre("ingotCopper", new ItemStack(ModItems.ingot, 1, 0));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.ingot, 1, 1));
        OreDictionary.registerOre("ingotTin", new ItemStack(ModItems.ingot, 1, 2));
        OreDictionary.registerOre("ingotCompressed", new ItemStack(ModItems.ingot, 1, 3));
        OreDictionary.registerOre("oreCopper", new ItemStack(ModBlocks.ore, 1, 0));
        OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.ore, 1, 1));
        OreDictionary.registerOre("oreTin", new ItemStack(ModBlocks.ore, 1, 2));
        OreDictionary.registerOre("blockCopper", new ItemStack(ModBlocks.block, 1, 0));
        OreDictionary.registerOre("blockSilver", new ItemStack(ModBlocks.block, 1, 1));
        OreDictionary.registerOre("blockTin", new ItemStack(ModBlocks.block, 1, 2));
        OreDictionary.registerOre("blockCompressed", new ItemStack(ModBlocks.block, 1, 3));
    }

}
