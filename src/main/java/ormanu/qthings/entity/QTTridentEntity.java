package ormanu.qthings.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ormanu.qthings.mixin.TridentEntityAccessor;

public class QTTridentEntity extends TridentEntity {
    public QTTridentEntity(EntityType<? extends QTTridentEntity> entityType, World world){
        super(entityType, world);
    }

    public QTTridentEntity(World world, PlayerEntity playerEntity, ItemStack stack) {
            super(world, playerEntity, stack);
    }

    public void setTridentAttributes(ItemStack stack) {
        this.setTridentStack(stack.copy());
        this.dataTracker.set(TridentEntityAccessor.qthings$getLoyalty(), (byte) EnchantmentHelper.getLoyalty(stack));
        this.dataTracker.set(TridentEntityAccessor.qthings$getEnchanted(), stack.hasGlint());

    }

    protected float getDragInWater() {
        return 0.99f;
    }
    public void setTridentStack(ItemStack tridentStack) {
        ((TridentEntityAccessor) this).qthings$setTridentStack(tridentStack);
    }

}
