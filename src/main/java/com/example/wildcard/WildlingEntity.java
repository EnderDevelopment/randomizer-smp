package com.example.wildcard;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;

public class WildlingEntity extends HostileEntity {
    public WildlingEntity(EntityType<? extends HostileEntity> type, World world) { super(type, world); }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, 24.0).add(EntityAttributes.ATTACK_DAMAGE, 5.0).add(EntityAttributes.MOVEMENT_SPEED, 0.29);
    }

    @Override protected void initGoals() {
        goalSelector.add(2, new MeleeAttackGoal(this, 1.15, false));
        goalSelector.add(7, new WanderAroundFarGoal(this, 0.8));
        goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }
}
