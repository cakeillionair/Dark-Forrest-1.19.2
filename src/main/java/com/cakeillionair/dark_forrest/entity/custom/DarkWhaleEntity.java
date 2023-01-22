package com.cakeillionair.dark_forrest.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class DarkWhaleEntity extends FlyingMob implements IAnimatable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    Vec3 moveTargetPoint = Vec3.ZERO;
    BlockPos anchorPoint = BlockPos.ZERO;
    DarkWhaleEntity.AttackPhase attackPhase = DarkWhaleEntity.AttackPhase.CIRCLE;
    static enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    public DarkWhaleEntity(EntityType<? extends FlyingMob> type, Level level) {
        super(type, level);
        //this.xpReward = 5;
        this.moveControl = new DarkWhaleEntity.WhaleMoveControl(this);
        this.lookControl = new DarkWhaleEntity.WhaleLookControl(this);
    }

    protected BodyRotationControl createBodyControl() {
        return new DarkWhaleEntity.WhaleBodyRotationControl(this);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 18.0f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.6f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new DarkWhaleEntity.WhaleAttackStrategyGoal());
        this.goalSelector.addGoal(2, new DarkWhaleEntity.WhaleSweepAttackGoal());
        this.goalSelector.addGoal(3, new DarkWhaleEntity.WhaleCircleAroundAnchorGoal());
        this.targetSelector.addGoal(1, new DarkWhaleEntity.WhaleAttackPlayerTargetGoal());
    }

    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33126_, DifficultyInstance p_33127_, MobSpawnType p_33128_, @Nullable SpawnGroupData p_33129_, @Nullable CompoundTag p_33130_) {
        this.anchorPoint = this.blockPosition().above(5);
        return super.finalizeSpawn(p_33126_, p_33127_, p_33128_, p_33129_, p_33130_);
    }

    public void readAdditionalSaveData(CompoundTag p_33132_) {
        super.readAdditionalSaveData(p_33132_);
        if (p_33132_.contains("AX")) {
            this.anchorPoint = new BlockPos(p_33132_.getInt("AX"), p_33132_.getInt("AY"), p_33132_.getInt("AZ"));
        }
    }

    public void addAdditionalSaveData(CompoundTag p_33141_) {
        super.addAdditionalSaveData(p_33141_);
        p_33141_.putInt("AX", this.anchorPoint.getX());
        p_33141_.putInt("AY", this.anchorPoint.getY());
        p_33141_.putInt("AZ", this.anchorPoint.getZ());
    }

    public boolean shouldRenderAtSqrDistance(double p_33107_) {
        return true;
    }

    public boolean canAttackType(EntityType<?> p_33111_) {
        return true;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.dark_whale.fly", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.dark_whale.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15f, 1.0f);
    }
    protected SoundEvent getAmbientSound() {return SoundEvents.WITHER_AMBIENT;}
    protected SoundEvent getHurtSound() {return SoundEvents.ENDER_DRAGON_HURT;}
    protected SoundEvent getDeathSound() {return SoundEvents.WITHER_DEATH;}
    protected float getSoundVolume() {return 0.2f;}
    
    /////////////
    /// GOALS ///
    /////////////
    class WhaleAttackPlayerTargetGoal extends Goal {
        private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
        private int nextScanTick = reducedTickDelay(20);

        public boolean canUse() {
            if (this.nextScanTick > 0) {
                --this.nextScanTick;
                return false;
            } else {
                this.nextScanTick = reducedTickDelay(60);
                List<Player> list = DarkWhaleEntity.this.level.getNearbyPlayers(this.attackTargeting, DarkWhaleEntity.this, DarkWhaleEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
                if (!list.isEmpty()) {
                    list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

                    for(Player player : list) {
                        if (DarkWhaleEntity.this.canAttack(player, TargetingConditions.DEFAULT)) {
                            DarkWhaleEntity.this.setTarget(player);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = DarkWhaleEntity.this.getTarget();
            return livingentity != null ? DarkWhaleEntity.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
        }
    }

    class WhaleAttackStrategyGoal extends Goal {
        private int nextSweepTick;

        public boolean canUse() {
            LivingEntity livingentity = DarkWhaleEntity.this.getTarget();
            return livingentity != null ? DarkWhaleEntity.this.canAttack(livingentity, TargetingConditions.DEFAULT) : false;
        }

        public void start() {
            this.nextSweepTick = this.adjustedTickDelay(10);
            DarkWhaleEntity.this.attackPhase = DarkWhaleEntity.AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        public void stop() {
            DarkWhaleEntity.this.anchorPoint = DarkWhaleEntity.this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, DarkWhaleEntity.this.anchorPoint).above(10 + DarkWhaleEntity.this.random.nextInt(20));
        }

        public void tick() {
            if (DarkWhaleEntity.this.attackPhase == DarkWhaleEntity.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    DarkWhaleEntity.this.attackPhase = DarkWhaleEntity.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = this.adjustedTickDelay((8 + DarkWhaleEntity.this.random.nextInt(4)) * 20);
                    DarkWhaleEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + DarkWhaleEntity.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            DarkWhaleEntity.this.anchorPoint = DarkWhaleEntity.this.getTarget().blockPosition().above(20 + DarkWhaleEntity.this.random.nextInt(20));
            if (DarkWhaleEntity.this.anchorPoint.getY() < DarkWhaleEntity.this.level.getSeaLevel()) {
                DarkWhaleEntity.this.anchorPoint = new BlockPos(DarkWhaleEntity.this.anchorPoint.getX(), DarkWhaleEntity.this.level.getSeaLevel() + 1, DarkWhaleEntity.this.anchorPoint.getZ());
            }

        }
    }

    class WhaleBodyRotationControl extends BodyRotationControl {
        public WhaleBodyRotationControl(Mob p_33216_) {
            super(p_33216_);
        }

        public void clientTick() {
            DarkWhaleEntity.this.yHeadRot = DarkWhaleEntity.this.yBodyRot;
            DarkWhaleEntity.this.yBodyRot = DarkWhaleEntity.this.getYRot();
        }
    }

    class WhaleCircleAroundAnchorGoal extends DarkWhaleEntity.WhaleMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        public boolean canUse() {
            return DarkWhaleEntity.this.getTarget() == null || DarkWhaleEntity.this.attackPhase == DarkWhaleEntity.AttackPhase.CIRCLE;
        }

        public void start() {
            this.distance = 5.0F + DarkWhaleEntity.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + DarkWhaleEntity.this.random.nextFloat() * 9.0F;
            this.clockwise = DarkWhaleEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        public void tick() {
            if (DarkWhaleEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + DarkWhaleEntity.this.random.nextFloat() * 9.0F;
            }

            if (DarkWhaleEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (DarkWhaleEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = DarkWhaleEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (DarkWhaleEntity.this.moveTargetPoint.y < DarkWhaleEntity.this.getY() && !DarkWhaleEntity.this.level.isEmptyBlock(DarkWhaleEntity.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (DarkWhaleEntity.this.moveTargetPoint.y > DarkWhaleEntity.this.getY() && !DarkWhaleEntity.this.level.isEmptyBlock(DarkWhaleEntity.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(DarkWhaleEntity.this.anchorPoint)) {
                DarkWhaleEntity.this.anchorPoint = DarkWhaleEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            DarkWhaleEntity.this.moveTargetPoint = Vec3.atLowerCornerOf(DarkWhaleEntity.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    class WhaleLookControl extends LookControl {
        public WhaleLookControl(Mob p_33235_) {
            super(p_33235_);
        }

        public void tick() {
        }
    }

    class WhaleMoveControl extends MoveControl {
        private float speed = 0.1F;

        public WhaleMoveControl(Mob p_33241_) {
            super(p_33241_);
        }

        public void tick() {
            if (DarkWhaleEntity.this.horizontalCollision) {
                DarkWhaleEntity.this.setYRot(DarkWhaleEntity.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = DarkWhaleEntity.this.moveTargetPoint.x - DarkWhaleEntity.this.getX();
            double d1 = DarkWhaleEntity.this.moveTargetPoint.y - DarkWhaleEntity.this.getY();
            double d2 = DarkWhaleEntity.this.moveTargetPoint.z - DarkWhaleEntity.this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d3) > (double)1.0E-5F) {
                double d4 = 1.0D - Math.abs(d1 * (double)0.7F) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = DarkWhaleEntity.this.getYRot();
                float f1 = (float)Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(DarkWhaleEntity.this.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180F / (float)Math.PI));
                DarkWhaleEntity.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                DarkWhaleEntity.this.yBodyRot = DarkWhaleEntity.this.getYRot();
                if (Mth.degreesDifferenceAbs(f, DarkWhaleEntity.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float)(-(Mth.atan2(-d1, d3) * (double)(180F / (float)Math.PI)));
                DarkWhaleEntity.this.setXRot(f4);
                float f5 = DarkWhaleEntity.this.getYRot() + 90.0F;
                double d6 = (double)(this.speed * Mth.cos(f5 * ((float)Math.PI / 180F))) * Math.abs(d0 / d5);
                double d7 = (double)(this.speed * Mth.sin(f5 * ((float)Math.PI / 180F))) * Math.abs(d2 / d5);
                double d8 = (double)(this.speed * Mth.sin(f4 * ((float)Math.PI / 180F))) * Math.abs(d1 / d5);
                Vec3 vec3 = DarkWhaleEntity.this.getDeltaMovement();
                DarkWhaleEntity.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
            }

        }
    }

    abstract class WhaleMoveTargetGoal extends Goal {
        public WhaleMoveTargetGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return DarkWhaleEntity.this.moveTargetPoint.distanceToSqr(DarkWhaleEntity.this.getX(), DarkWhaleEntity.this.getY(), DarkWhaleEntity.this.getZ()) < 4.0D;
        }
    }

    class WhaleSweepAttackGoal extends DarkWhaleEntity.WhaleMoveTargetGoal {
        private static final int CAT_SEARCH_TICK_DELAY = 20;
        private boolean isScaredOfCat;
        private int catSearchTick;

        public boolean canUse() {
            return DarkWhaleEntity.this.getTarget() != null && DarkWhaleEntity.this.attackPhase == DarkWhaleEntity.AttackPhase.SWOOP;
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = DarkWhaleEntity.this.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                if (livingentity instanceof Player) {
                    Player player = (Player)livingentity;
                    if (livingentity.isSpectator() || player.isCreative()) {
                        return false;
                    }
                }

                if (!this.canUse()) {
                    return false;
                } else {
                    if (DarkWhaleEntity.this.tickCount > this.catSearchTick) {
                        this.catSearchTick = DarkWhaleEntity.this.tickCount + 20;
                        List<Cat> list = DarkWhaleEntity.this.level.getEntitiesOfClass(Cat.class, DarkWhaleEntity.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);

                        for(Cat cat : list) {
                            cat.hiss();
                        }

                        this.isScaredOfCat = !list.isEmpty();
                    }

                    return !this.isScaredOfCat;
                }
            }
        }

        public void start() {
        }

        public void stop() {
            DarkWhaleEntity.this.setTarget((LivingEntity)null);
            DarkWhaleEntity.this.attackPhase = DarkWhaleEntity.AttackPhase.CIRCLE;
        }

        public void tick() {
            LivingEntity livingentity = DarkWhaleEntity.this.getTarget();
            if (livingentity != null) {
                DarkWhaleEntity.this.moveTargetPoint = new Vec3(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
                if (DarkWhaleEntity.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
                    DarkWhaleEntity.this.doHurtTarget(livingentity);
                    DarkWhaleEntity.this.attackPhase = DarkWhaleEntity.AttackPhase.CIRCLE;
                    if (!DarkWhaleEntity.this.isSilent()) {
                        DarkWhaleEntity.this.level.levelEvent(1039, DarkWhaleEntity.this.blockPosition(), 0);
                    }
                } else if (DarkWhaleEntity.this.horizontalCollision || DarkWhaleEntity.this.hurtTime > 0) {
                    DarkWhaleEntity.this.attackPhase = DarkWhaleEntity.AttackPhase.CIRCLE;
                }

            }
        }
    }
}
