package cji.boomarrow.items.entity;

import cji.boomarrow.BoomArrow;
import cji.boomarrow.items.entity.projectile.BoomArrowEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BoomArrow.MODID);

//    public static final EntityType<Arrow> ARROW =
//            register("arrow",
//                    EntityType.Builder.<Arrow>of(Arrow::new, MobCategory.MISC)
//                            .sized(0.5F, 0.5F)
//                            .clientTrackingRange(4)
//                            .updateInterval(20)
//            );

    public static final RegistryObject<EntityType<BoomArrowEntity>> BOOM_ARROW =
            ENTITIES.register("boom_arrow",
                    () -> EntityType.Builder.<BoomArrowEntity>of(BoomArrowEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build(new ResourceLocation(BoomArrow.MODID, "boom_arrow").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
