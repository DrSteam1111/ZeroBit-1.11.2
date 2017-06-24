package com.drsteam1111.zerobit.client.gui;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.container.ContainerBlockBreaker;
import com.drsteam1111.zerobit.network.PacketGetWorker;
import com.drsteam1111.zerobit.network.PacketHandler;
import com.drsteam1111.zerobit.tileentity.TileEntityBlockBreaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edvin on 2017-06-06.
 */
public class GuiBlockBreaker extends GuiContainer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Ref.MODID, "textures/gui/container/block_breaker.png");

    /**
     * The tile entity and player inventory
     */
    private TileEntityBlockBreaker te;
    private IInventory playerInv;

    public static int cooldown, maxCooldown = 0;

    public static int sync = 0;

    private ProgressBar progressBar;

    /**
     * Typical {@link GuiContainer} constructor
     *
     * @param playerInv The players inventory
     * @param te        The tile entity
     */
    public GuiBlockBreaker(IInventory playerInv, TileEntityBlockBreaker te) {
        super(new ContainerBlockBreaker(playerInv, te));

        this.xSize = 176; //Texture xSize
        this.ySize = 166; //Texture ySize

        this.te = te;
        this.playerInv = playerInv;

        this.progressBar = new ProgressBar(TEXTURE, ProgressBar.ProgressBarDirection.LEFT_TO_RIGHT, 14, 14, 135, 36, 176, 0);
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
     * Draws the text that is an overlay, i.e where it says Block Breaker in the gui on the top
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.block_breaker"); //Gets the formatted name for the block breaker from the language file
        this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
        this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name

        this.progressBar.setMin(cooldown).setMax(maxCooldown);
        this.progressBar.draw(this.mc);

        int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
        int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
        if (actualMouseX >= 134 && actualMouseX <= 149 && actualMouseY >= 17 && actualMouseY <= 32 && te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(9) == ItemStack.EMPTY) {
            List<String> text = new ArrayList<String>();
            text.add(TextFormatting.GRAY + I18n.format("gui.block_breaker.enchanted_book.tooltip"));
            this.drawHoveringText(text, actualMouseX, actualMouseY);
        }

        sync++;
        sync %= 10;
        if (sync == 0)
            PacketHandler.INSTANCE.sendToServer(new PacketGetWorker(this.te.getPos(), this.mc.player.getAdjustedHorizontalFacing(), "com.drsteam1111.zerobit.client.gui.GuiBlockBreaker", "cooldown", "maxCooldown"));

        this.mc.fontRendererObj.drawString(cooldown + " / " + maxCooldown, -50, 0, 0xFFFFFF);
    }
}