package com.cakeillionair.dark_forrest.client;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.entity.custom.DarkWhaleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DarkWhaleRenderer extends GeoEntityRenderer<DarkWhaleEntity> {
    public DarkWhaleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DarkWhaleModel());
        this.shadowRadius = 2.0f;
    }

    @Override
    public ResourceLocation getTextureLocation(DarkWhaleEntity animatable) {
        return new ResourceLocation(Dark_Forrest.MOD_ID, "textures/entity/dark_whale_texture.png");
    }

    @Override
    public RenderType getRenderType(DarkWhaleEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight,
                                    ResourceLocation texture) {
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
