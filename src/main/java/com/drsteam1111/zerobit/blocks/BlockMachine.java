package com.drsteam1111.zerobit.blocks;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.handlers.EnumHandler.MachineTypes;
import com.drsteam1111.zerobit.blocks.item.IMetaBlockName;
import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;


public abstract class BlockMachine extends BlockContainer implements IMetaBlockName, ITileEntityProvider {

    /**
     * The type which indicates the tier of block. Basic and advanced versions of each machine
     */
    public static final PropertyEnum TYPE = PropertyEnum.create("type", MachineTypes.class);

    public static boolean updateTileEntity;

    /**
     * Sets the default state, hardness and resistance for machines
     * @param unlocalizedName The unlocalized name of the block
     */
    public BlockMachine(String unlocalizedName) {
        super(Material.IRON); //The blocks material
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
        this.setHardness(3);
        this.setResistance(20);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, MachineTypes.BASIC));
        this.isBlockContainer = true;
    }

    public static boolean setTileEntityUpdate(boolean value) {
        return updateTileEntity = value;
    }

    public static boolean getTileEntityUpdate() {
        return updateTileEntity;
    }

    /**
     * Makes sure the block works with meta data
     */
    @Override
    public String getSpecialName(ItemStack stack) {
        return MachineTypes.values()[stack.getItemDamage() % MachineTypes.values().length].getName();
    }

    /**
     * Makes sure that in the creative tab there are items for all the different chip types
     */
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for(int i = 0; i < MachineTypes.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    /**
     * Makes sure that when you pick block you get the correct block
     */
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
                                  EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, (int) (getMetaFromState(world.getBlockState(pos))));
    }

    /**
     * Makes sure the block drops the correct damage. If the block has the {@link PropertyDirection} then this needs to be overridden
     */
    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    /**
     * Returns the meta data from the blockstate. If the block has the {@link PropertyDirection} then this needs to be overridden
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((MachineTypes)state.getValue(TYPE)).getID();
    }

    /**
     * Returns the state from the meta data. If the block has the {@link PropertyDirection} then this needs to be overridden
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, MachineTypes.values()[meta % MachineTypes.values().length]);
    }

    /**
     * Says the block has multiple types. If the block has the {@link PropertyDirection} then this needs to be overridden
     */
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }

    /**
     * Called by the {@link com.drsteam1111.zerobit.items.ItemChip} meaning that when you right click a machine with a chip, the machine will upgrade
     * @param world The world the block is in
     * @param player The player who right clicked
     * @param hand The player's hand (in game) which did the clicking
     * @param pos The position of the block
     * @param stack The {@link ItemStack} in the player's hand
     */
    public void updateMachineTier(World world, EntityPlayer player, EnumHand hand, BlockPos pos, ItemStack stack) {
        if(stack.getItem() == ModItems.chip) {
            MachineTypes newType = MachineTypes.values()[stack.getItemDamage() % MachineTypes.values().length];
            MachineTypes currentType = (MachineTypes) world.getBlockState(pos).getValue(TYPE);
            IBlockState newState = world.getBlockState(pos).withProperty(TYPE, newType);
            if(newType.getID() > currentType.getID()) {
                world.setBlockState(pos, newState, 2);
                updateTileEntity = true;
            }
            ItemStack newStack = stack.copy();
            newStack.shrink(1);
            player.setHeldItem(hand, newStack);
            if(player.getHeldItem(hand).getCount() <= 0)
                player.setHeldItem(hand, ItemStack.EMPTY); //Sets the stack to nothing if there stack size is 0 or smaller
            //stack.shrink(1); //NEW IN 1.11.2 - Before it would be .stackSize--
        }
    }

    /**
     * Says that the block should use a model to be rendered
     */
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Default method to create the tile entity. You probably want to override this
     */
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return null;
    }

    /**
     * Default method to create the tile entity. You probably want to override this
     */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

}

