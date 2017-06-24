package com.drsteam1111.zerobit.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by edvin on 2017-06-03.
 */
public class TileEntityCompressor extends TileEntity implements ICapabilityProvider {

    private ItemStackHandler handler;

    public TileEntityCompressor() {
        this.handler = new ItemStackHandler(5);
    }

    /**
     * Reads data from nbt where data is stored
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler")); // Gets the ItemStackHandler from tag within a tag
        super.readFromNBT(nbt);
    }

    /**
     * Writes data to nbt so it is stored
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("ItemStackHandler", this.handler.serializeNBT()); // We write our ItemStackHandler as a tag in a tag
        return super.writeToNBT(nbt);
    }

    /**
     * The packet which is used to update the tile entity which holds all of the
     * tileentities data
     */
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    /**
     * Reads the nbt when it receives a packet
     */
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    /**
     * Gets the nbt for a new packet
     */
    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    /**
     * Handles when you get an update
     */
    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    /**
     * Gets the tile entities nbt with all of the data stored in it
     */
    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }


    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) this.handler; // Makes it so that you can get the capability from our tile entity
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    /**
     * Says whether the player can interact with the block - used for our
     * {@link }
     *
     * @param player
     *            The player to test
     * @return If the player can interact with the block
     */
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.getPos()) == this
                && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }

}