package com.drsteam1111.zerobit.blocks.item;

import com.drsteam1111.zerobit.tileentity.TileEntityLevitator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Created by edvin on 2017-06-24.
 */
public class ItemBlockLevitator  extends ItemBlock {

    public ItemBlockLevitator(Block block) {
        super(block);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
                                float hitX, float hitY, float hitZ, IBlockState newState) {
        if (!world.setBlockState(pos, newState)) {
            return false;
        }
        if (world.getBlockState(pos).getBlock() == block) {
            world.getBlockState(pos).getBlock().onBlockPlacedBy(world, pos, newState, player, stack);
        }
        if (stack != null && stack.hasTagCompound()) {
            TileEntityLevitator lev = (TileEntityLevitator) world.getTileEntity(pos);
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("playerLev")
                    && tag.getBoolean("playerLev"))
                lev.playerLev = true;
        }
        return true;
    }

}
