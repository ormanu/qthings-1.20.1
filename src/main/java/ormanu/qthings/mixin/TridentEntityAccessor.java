package ormanu.qthings.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TridentEntity.class)
public interface TridentEntityAccessor {
    @Accessor("LOYALTY")
    static TrackedData<Byte> qthings$getLoyalty() {
        return null;
    }

    @Accessor("ENCHANTED")
    static TrackedData<Boolean> qthings$getEnchanted() {
        return null;
    }

    @Accessor("tridentStack")
    void qthings$setTridentStack(ItemStack stack);

    @Accessor("dealtDamage")
    boolean qthings$hasDealtDamage();

    @Accessor("dealtDamage")
    void qthings$setDealtDamage(boolean dealtDamage);

}