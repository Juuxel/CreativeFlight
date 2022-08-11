/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight.mixin;

import com.mojang.minecraft.gamemode.CreativeMode;
import com.mojang.minecraft.gamemode.GameMode;
import com.mojang.minecraft.player.Input;
import com.mojang.minecraft.player.Player;
import juuxel.creativeflight.FlyExtension;
import juuxel.creativeflight.PlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
abstract class PlayerMixin implements PlayerExtension {
    @Shadow public transient Input input;
    @Unique
    private transient boolean creativeFlight_flying;
    @Unique
    private transient boolean creativeFlight_wasFlying;
    @Unique
    private transient boolean creativeFlight_descending;
    @Unique
    private transient GameMode creativeFlight_gameMode;

    @Override
    public boolean creativeFlight_isFlying() {
        return creativeFlight_flying;
    }

    @Override
    public boolean creativeFlight_wasFlying() {
        boolean value = creativeFlight_wasFlying;
        creativeFlight_wasFlying = creativeFlight_flying;
        return value;
    }

    @Override
    public boolean creativeFlight_isDescending() {
        return creativeFlight_descending;
    }

    @Override
    public void creativeFlight_setGameMode(GameMode gameMode) {
        creativeFlight_gameMode = gameMode;
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lcom/mojang/minecraft/player/Input;tick()V"))
    private void onAiStep(CallbackInfo info) {
        if (creativeFlight_gameMode instanceof CreativeMode) {
            if (input instanceof FlyExtension) {
                creativeFlight_wasFlying = creativeFlight_flying;
                creativeFlight_flying = ((FlyExtension) input).creativeFlight_isFlying();
                creativeFlight_descending = ((FlyExtension) input).creativeFlight_isDescending();
            }
        } else {
            creativeFlight_flying = creativeFlight_wasFlying = creativeFlight_descending = false;
        }
    }
}
