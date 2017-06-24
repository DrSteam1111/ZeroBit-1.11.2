package com.drsteam1111.zerobit.creativetabs;

import com.drsteam1111.zerobit.Ref;
import com.drsteam1111.zerobit.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by edvin on 2017-06-01.
 */
public class ZeroBitTab extends CreativeTabs {

    public ZeroBitTab() {
        super(CreativeTabs.getNextID(), Ref.MODID);
        setBackgroundImageName("item_search.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.ingot, 1, 3);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

}
