package ormanu.qthings;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class QTItems {

    public static final Item Quetinomium = registerItem("quetinomium", new Item(new FabricItemSettings()));
    public static final Item QT_AXE =  registerItem("qtaxe", new AxeItem(QTToolMaterial.Quetinomium, 7, -2f,  new FabricItemSettings()));
    public static final Item QT_Scythe = registerItem("qtscythe", new SwordItem(QTToolMaterial.Quetinomium,8,-1f,new FabricItemSettings()));

    private static void addItemsToIngrredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(Quetinomium);
    }
    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        entries.add(QT_AXE);
        entries.add(QT_Scythe);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,new Identifier(QThings.MOD_ID, name), item);
    }
    public static void registerQTItemss() {
        QThings.LOGGER.info("Registering Items for " +  QThings.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(QTItems::addItemsToIngrredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(QTItems::addItemsToCombatItemGroup);
    }

}
