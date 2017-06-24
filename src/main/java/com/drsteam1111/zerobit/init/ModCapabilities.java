package com.drsteam1111.zerobit.init;

import com.drsteam1111.zerobit.capabilties.IWork;
import com.drsteam1111.zerobit.capabilties.Worker;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

/**
 * Created by edvin on 2017-06-06.
 */
public class ModCapabilities {

    @CapabilityInject(IWork.class)
    public static Capability<IWork> CAPABILITY_WORKER = null;

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IWork.class, new CapabilityWorker(), Worker.class);
    }

    public static class CapabilityWorker implements IStorage<IWork> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IWork> capability, IWork instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IWork> capability, IWork instance, EnumFacing side, NBTBase nbt) {

        }
    }

}
