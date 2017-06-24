package com.drsteam1111.zerobit.blocks;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.ZeroBit;
import com.drsteam1111.zerobit.blocks.item.IMetaBlockName;
import com.drsteam1111.zerobit.handlers.EnumHandler.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by edvin on 2017-06-01.
 */
public class BlockIngotBlocks extends Block implements IMetaBlockName {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", IngotTypes.class);

    public BlockIngotBlocks(String unlocalizedName) {
        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, IngotTypes.COPPER));
        this.setHardness(8F);
        this.setResistance(10F);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        IngotTypes type = (IngotTypes) state.getValue(TYPE);
        return type.getID();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, IngotTypes.values()[meta]);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for (int i = 0; i < IngotTypes.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return IngotTypes.values()[stack.getItemDamage()].getName();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
