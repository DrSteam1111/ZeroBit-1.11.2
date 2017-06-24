package com.drsteam1111.zerobit.proxy;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.network.PacketHandler;
import com.drsteam1111.zerobit.tileentity.TileEntityBlockBreaker;
import com.drsteam1111.zerobit.tileentity.TileEntityCompressor;
import com.drsteam1111.zerobit.tileentity.TileEntityLevitator;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by edvin on 2017-06-01.
 */
public class CommonProxy {

    public void preInit() {
        PacketHandler.registerMessages(Ref.MODID);
    }

    public void init() {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCompressor.class, Ref.MODID + ":compressor");
        GameRegistry.registerTileEntity(TileEntityBlockBreaker.class, Ref.MODID + ":block_breaker");
        GameRegistry.registerTileEntity(TileEntityLevitator.class, Ref.MODID + ":levirator");
    }


    public void registerRenders() {
    }

    public void registerModelBakeryVariants() {
    }

}
