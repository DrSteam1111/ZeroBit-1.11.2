package com.drsteam1111.zerobit.init;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.ZeroBit;
import com.drsteam1111.zerobit.handlers.EnumHandler.*;
import com.drsteam1111.zerobit.items.ItemChip;
import com.drsteam1111.zerobit.items.ItemIngots;
import com.drsteam1111.zerobit.items.ItemMaterials;
import com.drsteam1111.zerobit.items.ItemWand;
import com.drsteam1111.zerobit.util.Utils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by edvin on 2017-06-01.
 */
public class ModItems {

    public static Item ingot;
    public static Item chip;
    public static Item material;
    public static Item wand;

    public static void init() {
        ingot = new ItemIngots("ingot");
        chip = new ItemChip("chip");
        material = new ItemMaterials("zbmaterial");
        wand = new ItemWand("wand");
    }

    public static void register() {
        registerItem(ingot);
        registerItem(chip);
        registerItem(material);
        registerItem(wand);
    }

    public static void registerRenders() {
        registerRender(wand);

        for(int i = 0; i < MachineTypes.values().length; i++) {
            registerRender(chip, i, "chip_" + MachineTypes.values()[i].getName());
        }
        for (int i = 0; i < IngotTypes.values().length; i++) {
            registerRender(ingot, i, "ingot_" + IngotTypes.values()[i].getName());
        }
        for (int i = 0; i < MaterialTypes.values().length; i++) {
            registerRender(material, i, "zbmaterial_" + MaterialTypes.values()[i].getName());
        }
    }

    public static void registerItem(Item item) {
        item.setCreativeTab(ZeroBit.zeroBitTab);
        GameRegistry.register(item);
        Utils.getLogger().info("Registered Items: " + item.getUnlocalizedName().substring(5));
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Ref.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Register Item Render for: " + item.getUnlocalizedName().substring(5));
    }

    public static void registerRender(Item item, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Ref.MODID, fileName), "inventory"));
        Utils.getLogger().info("Register Item Render for: " + item.getUnlocalizedName().substring(5));
    }

}
