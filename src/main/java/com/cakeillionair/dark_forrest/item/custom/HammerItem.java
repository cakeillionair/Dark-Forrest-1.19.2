package com.cakeillionair.dark_forrest.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class HammerItem extends Item {
    public HammerItem(Properties properties) {
        super(properties);
    }
    /*public Level level;
    private Entity cachedOwner;
    private UUID ownerUUID;
    public void setOwner(@Nullable Entity entity) {
        if (entity != null) {
            this.ownerUUID = entity.getUUID();
            this.cachedOwner = entity;
        }

    }

    @Nullable
    public Entity getOwner() {
        if (this.cachedOwner != null && !this.cachedOwner.isRemoved()) {
            return this.cachedOwner;
        } else if (this.ownerUUID != null && this.level instanceof ServerLevel) {
            this.cachedOwner = ((ServerLevel)this.level).getEntity(this.ownerUUID);
            return this.cachedOwner;
        } else {
            return null;
        }
    }*/

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        /*Entity entity1 = this.getOwner();
        if (level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            //test lightning
            float f1 = 1.0F;
            SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
            Entity entity = this.getOwner();
            BlockPos blockpos = entity.blockPosition();
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
            lightningbolt.setCause(entity1 instanceof ServerPlayer ? (ServerPlayer)entity1 : null);
            this.level.addFreshEntity(lightningbolt);
            soundevent = SoundEvents.TRIDENT_THUNDER;
            f1 = 5.0F;
            player.getCooldowns().addCooldown(this, 20);
        }*/

        return super.use(level, player, hand);
    }
}
