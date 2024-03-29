package ormanu.qthings.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;
import ormanu.qthings.QThings;
import ormanu.qthings.QThingsClient;

@Environment(EnvType.CLIENT)
public class QTSoulforkEntityRenderer extends QTHellforkEntityRenderer {
    private final QTTridentModel model;

    public QTSoulforkEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new QTTridentModel(context.getPart(QThingsClient.MODEL_SOULFORK_LAYER));
    }

    public Identifier getTexture(TridentEntity tridentEntity) {
        return new Identifier(QThings.MOD_ID,"textures/entity/soulfork.png");
    }
}
