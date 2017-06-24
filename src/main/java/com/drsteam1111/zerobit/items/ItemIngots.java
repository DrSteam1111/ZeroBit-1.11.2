package com.drsteam1111.zerobit.items;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.ZeroBit;
import com.drsteam1111.zerobit.handlers.EnumHandler.IngotTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

/**
 * Created by edvin on 2017-06-01.
 */
public class ItemIngots extends Item {

    public ItemIngots(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < IngotTypes.values().length; i++) {
            items.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < IngotTypes.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + "." + IngotTypes.values()[i].getName();
            }
            else {
                continue;
            }
        }
        return this.getUnlocalizedName() + "." + IngotTypes.COPPER.getName();
    }
}
