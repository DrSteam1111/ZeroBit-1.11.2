package com.drsteam1111.zerobit.handlers;

import com.drsteam1111.zerobit.init.ModBlocks;
import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.drsteam1111.zerobit.handlers.EnumHandler.oreTypes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by edvin on 2017-06-01.
 */
public class RecipeHandler {

    public static void registerCraftingRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.block, 1, 0), new Object[] { "CCC", "CCC", "CCC", 'C', "ingotCopper"}));     //Copper
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.block, 1, 1), new Object[] { "SSS", "SSS", "SSS", 'S', "ingotSilver"}));    //Silver
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.block, 1, 2), new Object[] { "TTT", "TTT", "TTT", 'T', "ingotTin"}));    //Tin
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.block, 1, 3), new Object[] { "OOO", "OOO", "OOO", 'O', "ingotCompressed"}));    //Compressed
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compressor, 1), new Object[] { " A ", "INI", "RCR", 'A', Items.IRON_AXE, 'I', Blocks.IRON_BLOCK, 'N', Blocks.ANVIL, 'R', "dustRedstone", 'C', "ingotCopper"}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.breaker, 1, 0), new Object[] { "CFC", "CPC", "CTC", 'C', "cobblestone", 'F', new ItemStack(ModBlocks.machineFrame, 1, 0), 'P', Items.IRON_PICKAXE, 'T', "ingotTin"}));    //BASIC
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.breaker, 1, 1), new Object[] { "TFT", "TPT", "TST", 'T', "ingotTin", 'F', new ItemStack(ModBlocks.machineFrame, 1, 1), 'P', Items.DIAMOND_PICKAXE, 'S', "ingotSilver"}));    //ADVANCED
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineFrame, 1, 0), new Object[] { "GMG", "HCH", "GMG", 'G', "dyeGreen", 'M', "ingotCompressed", 'H', Blocks.HARDENED_CLAY, 'C', "chipBasic"})); //BASIC
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.machineFrame, 1, 1), new Object[] { "MCM", "SFS", "MRM", 'M', "ingotCompressed", 'C', "chipAdvanced", 'S', "ingotSilver", 'F', new ItemStack(ModBlocks.machineFrame, 1, 0), 'R', "dustRedstone"})); //ADVANCED
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chip, 1, 0), new Object[] { "TRT", "RGR", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'G', "dyeGreen" }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.chip, 1, 1), new Object[] { "TRT", "RCR", "TRT", 'T', "ingotTin", 'R', "dustRedstone", 'C', "chipBasic" }));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingot, 9, 0), new Object[] {"blockCopper"}));//Copper
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingot, 9, 1), new Object[] {"blockSilver"}));   //Silver
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingot, 9, 2), new Object[] {"blockTin"}));      //Tin
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingot, 9, 3), new Object[] {"blockCompressed"}));   //Compressed
    }

    public static void registerSmeltingRecipes() {
        for (int i = 0; i < oreTypes.values().length; i++) {
            GameRegistry.addSmelting(new ItemStack(ModBlocks.ore, 1, i), new ItemStack(ModItems.ingot, 1, i), 5.0F);
        }
        GameRegistry.addSmelting(new ItemStack(ModItems.material, 1, 1), new ItemStack(ModItems.material, 1, 0), 2.5F);
    }

}
