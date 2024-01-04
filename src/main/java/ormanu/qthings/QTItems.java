package ormanu.qthings;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import ormanu.qthings.entity.QTTridentEntity;
import ormanu.qthings.entity.QTEntityTypes;
import ormanu.qthings.item.HellForkItem;
import ormanu.qthings.item.SoulForkItem;
import ormanu.qthings.item.QTTridentItem;

import java.util.Objects;
import java.util.Set;
public class QTItems {

    public static final Set<QTTridentItem> ALL_TRIDENTS = new ReferenceOpenHashSet<>();
    public static final Item Quetinomium = registerItem("quetinomium", new Item(new FabricItemSettings()));
    public static final Item QT_Scythe = registerItem("qtscythe", new SwordItem(QTToolMaterial.Quetinomium,6,-3f,new FabricItemSettings().fireproof()));
    public static Item Hellfork;
    public static Item Soulfork;

    public static void init() {
        Hellfork = registerTrident(new HellForkItem((new Item.Settings()).maxDamage(350).fireproof(), QTEntityTypes.Hellforkentitytype), "hellfork", true);
        Soulfork = registerTrident(new SoulForkItem((new Item.Settings()).maxDamage(325).fireproof(), QTEntityTypes.Soulforkentitytype), "soulfork", true);
    }
    private static void addItemsToIngrredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(Quetinomium);
    }
    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(QT_Scythe);
        entries.add(Hellfork);
        entries.add(Soulfork);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,new Identifier(QThings.MOD_ID, name), item);
    }
    public static void registerQTItemss() {
        QThings.LOGGER.info("Registering Items for " +  QThings.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(QTItems::addItemsToIngrredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(QTItems::addItemsToCombatItemGroup);
    }
    public static QTTridentItem registerTrident(QTTridentItem item, String name, boolean registerDispenserBehavior) {
        Registry.register(Registries.ITEM, QThings.MOD_ID+ ":" + name, item);
        ALL_TRIDENTS.add(item);
        if (registerDispenserBehavior) {
            DispenserBlock.registerBehavior(item, new ProjectileDispenserBehavior() {
                @Override
                protected ProjectileEntity createProjectile(World world, Position position, ItemStack itemStack) {
                    QTTridentEntity tridentEntity = Objects.requireNonNull(item.getEntityType().create(world));
                    tridentEntity.setPos(position.getX(), position.getY(), position.getZ());
                    itemStack.decrement(1);
                    return tridentEntity;
                }
            });
        }
        return item;
    }
}