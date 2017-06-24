package com.drsteam1111.zerobit.events;

import cjminecraft.core.util.VersionChecker;
import com.drsteam1111.zerobit.Ref;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by edvin on 2017-06-06.
 */
public class PlayerEvents {

    @SubscribeEvent
    public void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
        VersionChecker.checkForUpdate(Ref.VERSION_CHECKER_URL, Ref.MODID, Ref.VERSION, event.player);
    }

}
