package ormanu.qthings.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class HellForkEntity extends QTTridentEntity {

    public HellForkEntity(EntityType<? extends HellForkEntity> entityType, World world) {
        super(entityType, world);
    }

    public HellForkEntity(World world, LivingEntity owner, ItemStack stack) {
        this(QTEntityTypes.Hellforkentitytype, world);
        this.setTridentAttributes(stack);
        this.setPosition(owner.getX(), owner.getEyeY() - (double)0.1f, owner.getZ());
        this.setOwner(owner);
        if (owner instanceof PlayerEntity) {
            this.pickupType = PickupPermission.ALLOWED;
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(8);
    }

    @Override
    public boolean isOnFire() {
        return true;
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isSubmergedInWater() && this.getWorld().isClient() && this.random.nextInt(5) == 0) {
            this.getWorld().addParticle(ParticleTypes.BUBBLE_COLUMN_UP, this.getX() + random.nextGaussian() / 10, this.getY() + random.nextGaussian() / 10, this.getZ() + random.nextGaussian() / 10, 0, this.random.nextFloat(), 0);
        }
    }
}
