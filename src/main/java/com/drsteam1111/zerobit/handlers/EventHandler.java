package com.drsteam1111.zerobit.handlers;

import com.drsteam1111.zerobit.events.PlayerEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by edvin on 2017-06-06.
 */
public class EventHandler {

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
    }

}
