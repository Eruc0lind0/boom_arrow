package cji.boomarrow.items.entity.projectile;

import cji.boomarrow.items.ModItems;
import cji.boomarrow.items.entity.ModEntityType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoomArrowEntity extends AbstractArrow {
    private final Item referenceItem;

    public BoomArrowEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
        this.referenceItem = ModItems.BOOM_ARROW.get();
    }

    public BoomArrowEntity(LivingEntity shooter, Level level, Item referenceItem) {
        super(ModEntityType.BOOM_ARROW.get(), shooter, level);
        this.referenceItem = referenceItem;
    }

    @Override
    public ItemStack getPickupItem() {
        return new ItemStack(this.referenceItem);
    }
}
