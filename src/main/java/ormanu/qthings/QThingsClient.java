package ormanu.qthings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import ormanu.qthings.entity.QTEntityTypes;
import ormanu.qthings.entity.QTSoulforkEntityRenderer;
import ormanu.qthings.entity.QTHellforkEntityRenderer;
import ormanu.qthings.entity.QTTridentModel;
import ormanu.qthings.item.QTTridentItem;
import ormanu.qthings.item.TridentItemRenderer;

public class QThingsClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_HELLFORK_LAYER = new EntityModelLayer(new Identifier(QThings.MOD_ID,"hellfork"),"main");
    public static final EntityModelLayer MODEL_SOULFORK_LAYER = new EntityModelLayer(new Identifier(QThings.MOD_ID, "soulfork"),"main");
    public static final EntityModelLayer Hellfork = new EntityModelLayer(new Identifier(QThings.MOD_ID, "hellfork"), "main");

    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(MODEL_HELLFORK_LAYER, QTTridentModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(QTEntityTypes.Hellforkentitytype, (context) -> {
            return new QTHellforkEntityRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(MODEL_SOULFORK_LAYER, QTTridentModel::getTexturedModelData);
        EntityRendererRegistry.INSTANCE.register(QTEntityTypes.Soulforkentitytype, (context) -> {
            return new QTSoulforkEntityRenderer(context);
        });

        for (QTTridentItem item : QTItems.ALL_TRIDENTS) {
            Identifier tridentId = Registries.ITEM.getId(item);
            Identifier texture = new Identifier(tridentId.getNamespace(), "textures/entity/" + tridentId.getPath() + ".png");
            EntityModelLayer modelLayer = item == QTItems.Hellfork ? Hellfork : EntityModelLayers.TRIDENT;

            TridentItemRenderer tridentItemRenderer = new TridentItemRenderer(tridentId, texture, modelLayer);
            ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(tridentItemRenderer);
            BuiltinItemRendererRegistry.INSTANCE.register(item, tridentItemRenderer);

            FabricModelPredicateProviderRegistry.register(item, new Identifier("throwing"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
            ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> out.accept(new ModelIdentifier(tridentId.getNamespace(), tridentId.getPath() + "_in_inventory", "inventory")));
        }


    }
}
