package com.drsteam1111.zerobit.proxy;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.config.ZbConfig;
import com.drsteam1111.zerobit.init.ModBlocks;
import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by edvin on 2017-06-01.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        ZbConfig.clientPreInit();
    }

    @Override
    public void registerRenders() {
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void registerModelBakeryVariants() {
        //Blocks\\
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.ore), new ResourceLocation(Ref.MODID, "ore_copper"), new ResourceLocation(Ref.MODID, "ore_silver"), new ResourceLocation(Ref.MODID, "ore_tin"));
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.block), new ResourceLocation(Ref.MODID, "block_copper"), new ResourceLocation(Ref.MODID, "block_silver"), new ResourceLocation(Ref.MODID, "block_tin"), new ResourceLocation(Ref.MODID, "block_compressed"));
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.breaker), new ResourceLocation(Ref.MODID, "block_breaker_basic"), new ResourceLocation(Ref.MODID, "block_breaker_advanced"));
    }
}
