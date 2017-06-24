package com.drsteam1111.zerobit.container.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by edvin on 2017-06-18.
 */
public class SlotCompressorFuel extends SlotItemHandler {
    public SlotCompressorFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return TileEntityFurnace.isItemFuel(stack) || isBucket(stack);
    }

    public int getItemStackLimit(ItemStack stack)
    {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    public static boolean isBucket(ItemStack stack)
    {
        return stack.getItem() == Items.BUCKET;
    }
}
