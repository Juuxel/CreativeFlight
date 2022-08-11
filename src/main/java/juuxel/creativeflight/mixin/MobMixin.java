package juuxel.creativeflight.mixin;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.mob.ai.AI;
import com.mojang.minecraft.mob.ai.BasicAI;
import juuxel.creativeflight.CreativeFlight;
import juuxel.creativeflight.FlyExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
abstract class MobMixin extends Entity {
    @Shadow public AI ai;

    private MobMixin(Level level) {
        super(level);
    }

    @Inject(method = "travel", at = @At(value = "INVOKE", target = "Lcom/mojang/minecraft/mob/Mob;move(FFF)V", ordinal = 2))
    private void onTravel(float g, float par2, CallbackInfo info) {
        if (this instanceof FlyExtension && ((FlyExtension) this).creativeFlight_isFlying()) {
            float yd;

            if (ai instanceof BasicAI && ((BasicAI) ai).jumping) {
                yd = 0.42F;
            } else if (((FlyExtension) this).creativeFlight_isDescending()) {
                yd = -0.42f;
            } else {
                yd = 0f;
            }

            this.yd = yd;
        }
    }

    @ModifyConstant(method = "travel", constant = @Constant(doubleValue = 0.08))
    private double onTravel_modifyFall(double fall) {
        if (this instanceof FlyExtension && ((FlyExtension) this).creativeFlight_isFlying()) {
            return 0;
        }

        return fall;
    }

    @ModifyConstant(method = "travel", constant = @Constant(floatValue = 0.02F))
    private float onTravel_modifySpeed(float speed) {
        if (this instanceof FlyExtension && ((FlyExtension) this).creativeFlight_isFlying()) {
            return speed * CreativeFlight.SPEED;
        }

        return speed;
    }
}
