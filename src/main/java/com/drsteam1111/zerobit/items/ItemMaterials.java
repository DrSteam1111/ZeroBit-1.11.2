package com.drsteam1111.zerobit.items;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.handlers.EnumHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * Created by edvin on 2017-06-17.
 */
public class ItemMaterials extends Item {

    public ItemMaterials(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumHandler.MaterialTypes.values().length; i++) {
            items.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < EnumHandler.MaterialTypes.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + "." + EnumHandler.MaterialTypes.values()[i].getName();
            }
            else {
                continue;
            }
        }
        return this.getUnlocalizedName() + "." + EnumHandler.MaterialTypes.RUBBER.getName();
    }
}
