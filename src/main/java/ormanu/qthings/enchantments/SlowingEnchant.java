package ormanu.qthings.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import ormanu.qthings.QTItems;

public class SlowingEnchant extends Enchantment {
    public SlowingEnchant() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON   , new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }
    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return QTItems.QT_Scythe.equals(stack.getItem());
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 2 * level, level - 1));
        }

        super.onTargetDamaged(user, target, level);
    }
}
