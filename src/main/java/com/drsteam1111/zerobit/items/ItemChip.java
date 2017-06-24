package com.drsteam1111.zerobit.items;


import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.blocks.BlockMachine;
import com.drsteam1111.zerobit.handlers.EnumHandler.MachineTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by edvin on 2017-06-06.
 */
public class ItemChip  extends Item {

    /**
     * Default constructor just sets the unlocalized name and the registry name
     * @param unlocalizedName
     */
    public ItemChip(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setHasSubtypes(true); //This just says the item has metadata
    }

    /**
     * Adds all the different versions of the item
     */
    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for(int i = 0; i < MachineTypes.values().length; i++) {
            items.add(new ItemStack(item, 1, i));
        }
    }

    /**
     * Gets the correct unlocalized name using the {@link MachineTypes} enum
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for(int i = 0; i < MachineTypes.values().length; i++) {
            if(stack.getItemDamage() == i) {
                return this.getUnlocalizedName() + "." + MachineTypes.values()[i].getName();
            }
            else {
                continue;
            }
        }
        return this.getUnlocalizedName() + "." + MachineTypes.BASIC.getName();
    }

    /**
     * Upgrades machines to the next tier of machine
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
                                      EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState state = world.getBlockState(pos);
        if(state != null) {
            if(state.getBlock() instanceof BlockMachine) {
                BlockMachine machine = (BlockMachine) state.getBlock();
                ItemStack heldStack = player.getHeldItem(hand);
                machine.updateMachineTier(world, player, hand, pos, heldStack);
            }
        }
        return EnumActionResult.PASS;
    }

}
