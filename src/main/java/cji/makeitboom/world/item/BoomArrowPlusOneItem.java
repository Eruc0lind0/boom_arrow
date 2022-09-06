package cji.makeitboom.world.item;

import cji.makeitboom.world.entity.ModEntityType;
import cji.makeitboom.world.entity.projectile.BoomArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class BoomArrowPlusOneItem extends ArrowItem {
    public final float damage;

    public BoomArrowPlusOneItem(Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity shooter) {
        float strength = 4f;
        BoomArrow arrow = new BoomArrow(
                ModEntityType.BOOM_ARROW.get(),
                shooter,
                level,
                ModItems.BOOM_ARROW_PLUS_ONE.get(),
                strength
        );
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = EnchantmentHelper.getTagEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == BoomArrowPlusOneItem.class;
    }
}
