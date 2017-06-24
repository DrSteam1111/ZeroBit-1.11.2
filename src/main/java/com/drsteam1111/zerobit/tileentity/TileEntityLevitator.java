package com.drsteam1111.zerobit.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by edvin on 2017-06-24.
 */
public class TileEntityLevitator extends TileEntity implements ITickable{

    public boolean playerLev = true;
    public int levitationHigh = 10;

    void doLevitate(Entity entity, int blockY) {
        int entityY = entity.getPosition().getY();
        if (!entity.isSneaking()) {
            if (entityY > blockY) {
                int dif = entityY - blockY;
                if (dif > 10) {
                    entity.setVelocity(entity.motionX, 0.4f / (dif - 5), entity.motionZ);
                }
                else {
                    entity.setVelocity(entity.motionX, 0.4f, entity.motionZ);
                }

            }
            if (entityY == blockY) {
                entity.setVelocity(entity.motionX, 0.4f, entity.motionZ);
            }
        }
        else {
            if (entityY > blockY) {
                int dif = entityY - blockY;
                if (dif > 2) {
                    entity.setVelocity(entity.motionX, -0.4f, entity.motionZ);
                }
                else {
                    entity.setVelocity(entity.motionX, -0.2f, entity.motionZ);
                }
            }
            else {
                entity.setVelocity(entity.motionX, -0.1f, entity.motionZ);
            }

        }
        entity.fallDistance = 0f;
    }

    @Override
    public void update() {
        AxisAlignedBB area = new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + levitationHigh, pos.getZ() + 1);
        int blockY = this.pos.getY();
        if (playerLev) {
            List<EntityPlayer> entities = world.getEntitiesWithinAABB(EntityPlayer.class, area);
            for (EntityPlayer entity : entities) {
                doLevitate(entity, blockY);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        playerLev = nbt.getBoolean("playerLev");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("playerLev", playerLev);
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound getTileData() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

}
