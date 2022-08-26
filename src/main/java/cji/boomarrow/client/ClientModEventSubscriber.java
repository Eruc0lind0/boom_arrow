package cji.boomarrow.client;

import cji.boomarrow.BoomArrow;
import cji.boomarrow.client.renderer.entity.BoomArrowRenderer;
import cji.boomarrow.items.entity.ModEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BoomArrow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value= Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.BOOM_ARROW.get(), BoomArrowRenderer::new);
    }
}
