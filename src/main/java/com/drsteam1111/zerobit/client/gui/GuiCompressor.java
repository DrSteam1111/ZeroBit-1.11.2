package com.drsteam1111.zerobit.client.gui;

import java.util.ArrayList;
import java.util.List;
import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.container.ContainerCompressor;
import com.drsteam1111.zerobit.tileentity.TileEntityCompressor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Created by edvin on 2017-06-03.
 */
public class GuiCompressor  extends GuiContainer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/container/compressor.png");

    /**
     * The tile entity and player inventory
     */
    private TileEntityCompressor te;
    private IInventory playerInv;

    public static int cooldown, maxCooldown = 0;

    private ProgressBar progressBar;

    /**
     * Typical {@link GuiContainer} constructor
     * @param playerInv The players inventory
     * @param te The tile entity
     */
    public GuiCompressor(IInventory playerInv, TileEntityCompressor te) {
        super(new ContainerCompressor(playerInv, te));

        this.xSize = 176; //Texture xSize
        this.ySize = 166; //Texture ySize

        this.te = te;
        this.playerInv = playerInv;

        this.progressBar = new ProgressBar(TEXTURE, ProgressBar.ProgressBarDirection.LEFT_TO_RIGHT, 8, 8, 102, 39, 176, 0);
    }

    /**
     * Draws the gui and the grey background behind it
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); //Grey background
        this.mc.getTextureManager().bindTexture(TEXTURE); //Binds the texture for rendering
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize); //Draws our texture
    }

    /**
     * Draws the text that is an overlay, i.e where it says compressor in the gui on the top
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.compressor"); //Gets the formatted name for the block breaker from the language file
        this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) - 22, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
        this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name

        this.progressBar.setMin(cooldown).setMax(maxCooldown);
        this.progressBar.draw(this.mc);
    }
}