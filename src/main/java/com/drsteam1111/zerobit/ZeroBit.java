package com.drsteam1111.zerobit;

import com.drsteam1111.zerobit.client.gui.GuiHandler;
import com.drsteam1111.zerobit.config.ZbConfig;
import com.drsteam1111.zerobit.creativetabs.ZeroBitTab;
import com.drsteam1111.zerobit.handlers.OreDictionaryHandler;
import com.drsteam1111.zerobit.handlers.RecipeHandler;
import com.drsteam1111.zerobit.init.ModBlocks;
import com.drsteam1111.zerobit.init.ModCapabilities;
import com.drsteam1111.zerobit.init.ModItems;
import com.drsteam1111.zerobit.proxy.CommonProxy;
import com.drsteam1111.zerobit.util.Utils;
import com.drsteam1111.zerobit.worldgen.OreGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Made by DrSteam1111
 */

@Mod(modid = Ref.MODID, name = Ref.NAME, version = Ref.VERSION, guiFactory = Ref.GUI_FACTORY, acceptedMinecraftVersions = Ref.ACCEPTED_MC_VERSIONS, useMetadata = true, customProperties = { @CustomProperty(k = "useVersionChecker", v = "true") })
public class ZeroBit {

    @Mod.Instance
    public static ZeroBit instance;

    @SidedProxy(serverSide = Ref.SERVER_PROXY_PATH, clientSide = Ref.CLIENT_PROXY_PATH)
    public static CommonProxy proxy;

    public static ZeroBitTab zeroBitTab;

    static com.drsteam1111.zerobit.handlers.EventHandler eventHandler = new com.drsteam1111.zerobit.handlers.EventHandler();

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Utils.getLogger().info("Pre Initialization");
        zeroBitTab = new ZeroBitTab();
        ModItems.init();
        ModBlocks.init();
        ModItems.register();
        ModBlocks.register();

        ZbConfig.preInit();
        proxy.preInit();
        proxy.registerRenders();
        proxy.registerTileEntities();

        NetworkRegistry.INSTANCE.registerGuiHandler(ZeroBit.instance, new GuiHandler());
        ModCapabilities.registerCapabilities();
        Utils.getLogger().info("Pre Initialization Done");
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {
        Utils.getLogger().info("Initialization");
        proxy.init();
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
        OreDictionaryHandler.registerOreDictionary();
        proxy.registerModelBakeryVariants();
        eventHandler.registerEvents();
        RecipeHandler.registerCraftingRecipes();
        RecipeHandler.registerSmeltingRecipes();
        Utils.getLogger().info("Initialization Done");
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        Utils.getLogger().info("Post Initialization");
        Utils.getLogger().info("Post Initialization Done");
    }

}
