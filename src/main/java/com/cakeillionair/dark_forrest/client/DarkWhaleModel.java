package com.cakeillionair.dark_forrest.client;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.entity.custom.DarkWhaleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DarkWhaleModel extends AnimatedGeoModel<DarkWhaleEntity> {
    @Override
    public ResourceLocation getModelResource(DarkWhaleEntity object) {
        return new ResourceLocation(Dark_Forrest.MOD_ID, "geo/dark_whale.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DarkWhaleEntity object) {
        return new ResourceLocation(Dark_Forrest.MOD_ID, "textures/entity/dark_whale_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DarkWhaleEntity animatable) {
        return new ResourceLocation(Dark_Forrest.MOD_ID, "animations/dark_whale.animation,json");
    }
}
