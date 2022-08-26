package cji.boomarrow.item;

import cji.boomarrow.BoomArrow;
import cji.boomarrow.misc.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BoomArrow.MODID);

    public static final RegistryObject<Item> BOOM_ARROW = ITEMS.register("boom_arrow",
            () -> new ItemBoomArrow(new Item.Properties()
                    .tab(ModCreativeModeTab.BOOM_ARROW_TAB)
                    .stacksTo(64)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
