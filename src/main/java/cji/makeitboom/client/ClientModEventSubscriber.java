package cji.makeitboom.client;

import cji.makeitboom.MakeItBoom;
import cji.makeitboom.client.renderer.entity.BoomArrowPlusOneRenderer;
import cji.makeitboom.client.renderer.entity.BoomArrowPlusTwoRenderer;
import cji.makeitboom.client.renderer.entity.BoomArrowRenderer;
import cji.makeitboom.world.entity.ModEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = MakeItBoom.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value= Dist.CLIENT
)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityType.BOOM_ARROW.get(), BoomArrowRenderer::new);
        event.registerEntityRenderer(ModEntityType.BOOM_ARROW_PLUS_ONE.get(), BoomArrowPlusOneRenderer::new);
        event.registerEntityRenderer(ModEntityType.BOOM_ARROW_PLUS_TWO.get(), BoomArrowPlusTwoRenderer::new);
    }
}
