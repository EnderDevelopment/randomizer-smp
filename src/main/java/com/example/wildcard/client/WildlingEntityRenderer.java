package com.example.wildcard.client;

import com.example.wildcard.WildlingEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class WildlingEntityRenderer extends MobEntityRenderer<WildlingEntity, BipedEntityModel<WildlingEntity>> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/zombie/zombie.png");

    public WildlingEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5f);
    }

    @Override public Identifier getTexture(WildlingEntity entity) { return TEXTURE; }
}
