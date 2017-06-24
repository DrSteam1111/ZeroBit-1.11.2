package com.drsteam1111.zerobit.blocks;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.init.ModBlocks;
import com.drsteam1111.zerobit.tileentity.TileEntityLevitator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edvin on 2017-06-24.
 */
public class BlockLevitator extends BlockContainer {

    public BlockLevitator(String unlocalizedName) {
        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setHardness(8F);
        this.setResistance(10F);
        this.isBlockContainer = true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityLevitator();
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLevitator();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return new ArrayList<>();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return getStack((TileEntityLevitator) world.getTileEntity(pos));
    }

    ItemStack getStack(TileEntityLevitator lev) {
        ItemStack stack = new ItemStack(ModBlocks.levitator);
        NBTTagCompound tag = new NBTTagCompound();
        if (lev != null) {
            if (lev.playerLev)
                tag.setBoolean("playerLev", true);
        }

        stack.setTagCompound(tag);

        return stack;
    }



}

