package com.drsteam1111.zerobit.blocks.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by edvin on 2017-06-06.
 */
public class ItemBlockBreaker extends ItemBlock {

    /**
     * Default {@link ItemBlock} constructor
     *
     * @param block The original block
     */
    public ItemBlockBreaker(Block block) {
        super(block);
        if (!(block instanceof IMetaBlockName)) { //Makes sure that the block implements IMetaBlockName
            throw new IllegalArgumentException(String.format("The given Block %s is not an instance of IMetaBlockName!", block.getUnlocalizedName()));
        }
        this.setHasSubtypes(true); //Says the block has meta data
        this.setMaxDamage(0);
    }

    /**
     * Changes the unlocalized name
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
    }

    /**
     * Fixes a bug with not placing the correct variant of the block
     * THIS IS NEEDED
     */
    public int getMetadata(int damage) {
        return damage;
    }
}
