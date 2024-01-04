package ormanu.qthings.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ormanu.qthings.QThings;

public class QTEntityTypes {

    public static EntityType<HellForkEntity> Hellforkentitytype;
    public static EntityType<SoulForkEntity> Soulforkentitytype;


    public static void init() {
        Hellforkentitytype = register("hellfork", createEntityType(HellForkEntity::new));
        Soulforkentitytype = register("soulfork", createEntityType(SoulForkEntity::new));
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> bombEntityType) {
        return Registry.register(Registries.ENTITY_TYPE, QThings.MOD_ID + ":" + s, bombEntityType);
    }
    private static <T extends Entity> EntityType<T> createEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.changing(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }
}
