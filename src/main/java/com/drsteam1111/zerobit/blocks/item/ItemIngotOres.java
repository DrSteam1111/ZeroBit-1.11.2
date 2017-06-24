package com.drsteam1111.zerobit.blocks.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by edvin on 2017-06-01.
 */
public class ItemIngotOres  extends ItemBlock {

    public ItemIngotOres(Block block) {
        super(block);
        if(!(block instanceof IMetaBlockName)) {
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of IMetaBlockName!", block.getUnlocalizedName()));
        }
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + ((IMetaBlockName)this.block).getSpecialName(stack);
    }

    public int getMetadata(int damage) {
        return damage;
    }
}

