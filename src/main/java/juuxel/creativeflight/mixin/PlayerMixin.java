/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight.mixin;

import com.mojang.minecraft.player.Input;
import com.mojang.minecraft.player.Player;
import juuxel.creativeflight.FlyExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
abstract class PlayerMixin implements FlyExtension {
    @Shadow public transient Input input;
    @Unique
    private transient boolean flying;
    @Unique
    private transient boolean wasFlying;
    @Unique
    private transient boolean descending;

    @Override
    public boolean creativeFlight_isFlying() {
        return flying;
    }

    @Override
    public boolean creativeFlying_wasFlying() {
        boolean value = wasFlying;
        wasFlying = flying;
        return value;
    }

    @Override
    public boolean creativeFlight_isDescending() {
        return descending;
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lcom/mojang/minecraft/player/Input;a()V"))
    private void onAiStep(CallbackInfo info) {
        if (input instanceof FlyExtension) {
            wasFlying = flying;
            flying = ((FlyExtension) input).creativeFlight_isFlying();
            descending = ((FlyExtension) input).creativeFlight_isDescending();
        }
    }
}
