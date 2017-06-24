package com.drsteam1111.zerobit.container.slot;

import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by edvin on 2017-06-03.
 */
public class SlotIngots extends SlotItemHandler {

    public SlotIngots(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return super.isItemValid(stack) && stack.getItem() == ModItems.ingot;
    }

}