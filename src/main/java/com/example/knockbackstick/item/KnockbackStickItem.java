package com.example.knockbackstick.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            World world = player.getWorld();

            // Рассчитываем направление откидывания
            Vec3d direction = target.getPos().subtract(attacker.getPos()).normalize();
            double knockbackForce = 15.0;
            Vec3d knockbackVelocity = new Vec3d(
                    direction.x * knockbackForce,
                    Math.max(1.5, direction.y * knockbackForce),
                    direction.z * knockbackForce
            );

            target.setVelocity(knockbackVelocity);
            target.velocityModified = true;

            // Звук
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK,
                    player.getSoundCategory(), 1.0F, 1.2F);
        }
    }
}
