package cji.makeitboom.client.renderer.entity;

import cji.makeitboom.MakeItBoom;
import cji.makeitboom.world.entity.projectile.BoomArrowPlusTwo;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomArrowPlusTwoRenderer extends ArrowRenderer<BoomArrowPlusTwo> {
    public BoomArrowPlusTwoRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BoomArrowPlusTwo entity) {
        Item referenceItem = entity.getPickupItem().getItem();
        String refItemDesc = referenceItem.getDescriptionId();
        return new ResourceLocation(
                MakeItBoom.MODID,
                "textures/entity/projectiles/" + refItemDesc.substring(refItemDesc.lastIndexOf(".") + 1) + ".png"
        );
    }
}
