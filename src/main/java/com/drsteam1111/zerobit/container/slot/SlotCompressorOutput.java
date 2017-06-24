package com.drsteam1111.zerobit.container.slot;


import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by edvin on 2017-06-03.
 */
public class SlotCompressorOutput extends SlotItemHandler {

    public SlotCompressorOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }




    /**
     * Future Ref
     * {@link net.minecraft.inventory.SlotFurnaceOutput}
     * {@link ContainerFurnace}
     * {@link TileEntityFurnace}
     */
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }

}