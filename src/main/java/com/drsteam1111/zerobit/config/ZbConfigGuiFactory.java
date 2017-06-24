package com.drsteam1111.zerobit.config;

import com.drsteam1111.zerobit.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by edvin on 2017-06-06.
 */
public class ZbConfigGuiFactory implements IModGuiFactory{

    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ZbConfigGui.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Nullable
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    @Override
    public boolean hasConfigGui() {
        return true;
    }

    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return new ZbConfigGui(parentScreen);
    }

    public static class ZbConfigGui extends GuiConfig {

        public ZbConfigGui(GuiScreen parentScreen) {
            super(parentScreen, getConfigElements(), Ref.MODID, false, false, I18n.format("gui.config.main_title"));
        }

        private static List<IConfigElement> getConfigElements() {
            List<IConfigElement> list = new ArrayList<IConfigElement>();
            list.add(new DummyCategoryElement(I18n.format("gui.config.category.blocks"), "gui.config.category.blocks", CategoryEntryBlocks.class));
            return list;
        }

        public static class CategoryEntryBlocks extends CategoryEntry {

            public CategoryEntryBlocks(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
                super(owningScreen, owningEntryList, configElement);
            }

            @Override
            protected GuiScreen buildChildScreen() {
                Configuration config = ZbConfig.getConfig();
                ConfigElement categoryBlocks = new ConfigElement(config.getCategory(ZbConfig.CATEGORY_NAME_BLOCKS));
                List<IConfigElement> propertiesOnScreen = categoryBlocks.getChildElements();
                String windowTitle = I18n.format("gui.config.category.blocks");
                return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID, this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart, this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
            }
        }
    }

}
