package com.drsteam1111.zerobit.client.gui;

import com.drsteam1111.zerobit.container.ContainerBlockBreaker;
import com.drsteam1111.zerobit.container.ContainerCompressor;
import com.drsteam1111.zerobit.tileentity.TileEntityBlockBreaker;
import com.drsteam1111.zerobit.tileentity.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by edvin on 2017-06-03.
 */
public class GuiHandler implements IGuiHandler {

    /**
     * Each gui needs an ID
     */
    public static final int COMPRESSOR = 0;
    public static final int BLOCK_BREAKER = 1;

    /**
     * Should return the container for that gui. This is called server side because servers handle items in guis
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == COMPRESSOR) {
            return new ContainerCompressor(player.inventory, (TileEntityCompressor) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == BLOCK_BREAKER) {
            return new ContainerBlockBreaker(player.inventory, (TileEntityBlockBreaker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    /**
     * Should return the actual gui. This is called client side as thats where guis are rendered
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == COMPRESSOR) {
            return new GuiCompressor(player.inventory, (TileEntityCompressor) world.getTileEntity(new BlockPos(x, y, z)));
        }
        if(ID == BLOCK_BREAKER) {
            return new GuiBlockBreaker(player.inventory, (TileEntityBlockBreaker) world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

}
