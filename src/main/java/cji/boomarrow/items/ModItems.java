package cji.boomarrow.items;

import cji.boomarrow.BoomArrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BoomArrow.MODID);

    public static final RegistryObject<Item> BOOM_ARROW = ITEMS.register("BOOM_ARROW",
            () -> new Item(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MISC)
                    .stacksTo(64)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
