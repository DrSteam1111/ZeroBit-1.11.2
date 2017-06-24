package com.drsteam1111.zerobit.items;


import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.util.Utils;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import scala.Int;

/**
 * Created by edvin on 2017-06-23.
 */
public class ItemWand extends Item {

    private float teleportVolume = 1.0f;

    public ItemWand(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Ref.MODID, unlocalizedName));
    }

    private void playPortSound(EntityPlayer player) {
        if (teleportVolume >= 0.01) {
            player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, teleportVolume, 1.0f);
            Utils.getLogger().info("Played sound");
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            int distance = 50;
            Vec3d lookVec = player.getLookVec();
            Vec3d start = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
            if (player.isSneaking()) {
                distance /= 2;
            }
            Vec3d end = start.addVector(lookVec.xCoord * distance, lookVec.yCoord * distance, lookVec.zCoord * distance);
            RayTraceResult position = world.rayTraceBlocks(start, end);
            if (position == null) {
                player.setPositionAndUpdate(end.xCoord , end.yCoord, end.zCoord);
            } else {
                BlockPos blockPos = position.getBlockPos();
                int x = blockPos.getX();
                int y = blockPos.getY();
                int z = blockPos.getZ();
                if (world.isAirBlock(blockPos.up()) && world.isAirBlock(blockPos.up(2))) {
                    player.setPositionAndUpdate(x+.5, y + 1, z+.5);
                    playPortSound(player);
                } else {
                    switch (position.sideHit) {
                        case DOWN:
                            player.setPositionAndUpdate(x+.5, y - 2, z+.5);
                            playPortSound(player);
                            break;
                        case UP:
                            return new ActionResult(EnumActionResult.FAIL, this);
                        case NORTH:
                            player.setPositionAndUpdate(x+.5, y, z - 1 + .5);
                            playPortSound(player);
                            break;
                        case SOUTH:
                            player.setPositionAndUpdate(x+.5, y, z + 1+.5);
                            playPortSound(player);
                            break;
                        case WEST:
                            player.setPositionAndUpdate(x - 1+.5, y, z+.5);
                            playPortSound(player);
                            break;
                        case EAST:
                            player.setPositionAndUpdate(x + 1+.5, y, z+.5);
                            playPortSound(player);
                            break;
                    }
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, new ItemStack(this));
    }
}
