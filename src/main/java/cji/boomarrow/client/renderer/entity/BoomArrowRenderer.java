package cji.boomarrow.client.renderer.entity;

import cji.boomarrow.BoomArrow;
import cji.boomarrow.items.entity.BoomArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoomArrowRenderer extends ArrowRenderer<BoomArrowEntity> {
    public BoomArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BoomArrowEntity entity) {
        Item referenceItem = entity.getPickupItem().getItem();
        String refItemDesc = referenceItem.getDescriptionId();
        return new ResourceLocation(
                BoomArrow.MODID,
                "textures/item/" + refItemDesc.substring(refItemDesc.lastIndexOf(".") + 1) + ".png"
        );
    }
}
