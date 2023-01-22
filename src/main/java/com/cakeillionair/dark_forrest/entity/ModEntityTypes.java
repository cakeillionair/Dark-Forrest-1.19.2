package com.cakeillionair.dark_forrest.entity;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.entity.custom.DarkWhaleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Dark_Forrest.MOD_ID);

    public static final RegistryObject<EntityType<DarkWhaleEntity>> DARK_WHALE =
            ENTITY_TYPES.register("dark_whale",
                    () -> EntityType.Builder.of(DarkWhaleEntity::new, MobCategory.MONSTER)
                            .sized(4.0f, 1.5f)
                            .build(new ResourceLocation(Dark_Forrest.MOD_ID, "dark_whale").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
