package com.drsteam1111.zerobit.blocks;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.ZeroBit;
import com.drsteam1111.zerobit.client.gui.GuiHandler;
import com.drsteam1111.zerobit.tileentity.TileEntityCompressor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by edvin on 2017-06-01.
 */
public class BlockCompressor extends Block implements ITileEntityProvider{

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockCompressor(String unlocalizedName) {
        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(5.0F);
        this.setResistance(4.5F);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityCompressor();
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCompressor();
    }

    /**
     * Returns the correct meta for the block
     * I recommend also saving the EnumFacing to the meta but I haven't
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing facing = (EnumFacing) state.getValue(FACING);
        int meta = EnumFacing.values().length + facing.ordinal(); //Stores the type the EnumFacing in the meta
        return meta;
    }

    /**
     * Gets the block state from the meta
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.values()[meta % EnumFacing.values().length]; //Gets the EnumFacing from the meta
        return this.getDefaultState().withProperty(FACING, facing); //Returns the correct state
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    /**
     * Opens our block's gui when the player right clicks on the block
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
                                    EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
        if(!worldIn.isRemote) {
            playerIn.openGui(ZeroBit.instance, GuiHandler.COMPRESSOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

}