package cji.makeitboom.world.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MAKE_IT_BOOM_TAB = new CreativeModeTab("makeitboomtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BOOM_ARROW.get());
        }
    };
}
