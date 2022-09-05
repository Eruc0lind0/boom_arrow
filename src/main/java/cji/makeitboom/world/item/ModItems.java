package cji.makeitboom.world.item;

import cji.makeitboom.MakeItBoom;
import cji.makeitboom.world.item.BoomArrowItem;
import cji.makeitboom.world.item.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MakeItBoom.MODID);

    public static final RegistryObject<Item> BOOM_ARROW = ITEMS.register("boom_arrow",
            () -> new BoomArrowItem(new Item.Properties()
                    .tab(ModCreativeModeTab.MAKE_IT_BOOM_TAB)
                    .stacksTo(64), 100f));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
