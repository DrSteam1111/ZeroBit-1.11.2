package com.drsteam1111.zerobit.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by edvin on 2017-06-06.
 */
public class PacketHandler {

    /**
     * The instance of packet handler, for use to be able to send messages
     */
    public static SimpleNetworkWrapper INSTANCE;

    /**
     * The unique ID tracker for our packets
     */
    private static int ID = 0;

    /**
     * Get the next id
     *
     * @return The next id
     */
    private static int nextID() {
        return ID++;
    }

    /**
     * Register all of our network messages on their appropriate side
     *
     * @param channelName
     *            The name of the network channel
     */
    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

        // Server packets
        INSTANCE.registerMessage(PacketGetWorker.Handler.class, PacketGetWorker.class, nextID(), Side.SERVER);

        // Client packets
        INSTANCE.registerMessage(PacketReturnWorker.Handler.class, PacketReturnWorker.class, nextID(), Side.CLIENT);
    }

}
