package cji.boomarrow.tab;

import cji.boomarrow.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab BOOM_ARROW_TAB = new CreativeModeTab("boomarrowtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BOOM_ARROW.get());
        }
    };
}
