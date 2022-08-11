/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.creativeflight.mixin;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.Options;
import com.mojang.minecraft.input.Keybind;
import juuxel.creativeflight.CreativeFlight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.Arrays;

@Mixin(Options.class)
abstract class OptionsMixin {
    @Shadow public Keybind[] keybinds;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/minecraft/Options;load()V"))
    private void onConstruct(Minecraft mc, File file, CallbackInfo info) {
        int size = keybinds.length;
        keybinds = Arrays.copyOf(keybinds, size + 2);
        keybinds[size] = CreativeFlight.FLYING_KEYBIND;
        keybinds[size + 1] = CreativeFlight.DESCENDING_KEYBIND;
    }
}
