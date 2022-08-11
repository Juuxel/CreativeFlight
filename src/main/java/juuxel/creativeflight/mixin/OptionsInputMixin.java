/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight.mixin;

import com.mojang.minecraft.player.Input;
import com.mojang.minecraft.player.OptionsInput;
import juuxel.creativeflight.CreativeFlight;
import juuxel.creativeflight.FlyExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsInput.class)
abstract class OptionsInputMixin extends Input implements FlyExtension {
    @Unique
    private boolean creativeFlight_flying;
    @Unique
    private boolean creativeFlight_descending;

    @Override
    public boolean creativeFlight_isFlying() {
        return creativeFlight_flying;
    }

    @Override
    public boolean creativeFlight_isDescending() {
        return creativeFlight_descending;
    }

    @Inject(method = "a(IZ)V", at = @At("RETURN"))
    private void onSetKey(int key, boolean down, CallbackInfo info) {
        if (down && key == CreativeFlight.FLYING_KEYBIND.key) {
            creativeFlight_flying = !creativeFlight_flying;
        }

        if (key == CreativeFlight.DESCENDING_KEYBIND.key) {
            creativeFlight_descending = down;
        }
    }
}
